package com.zahi.themovieapp.dynamiclink

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks

class DynamicLink {

    private fun getDeepLink(scheme: String, key: String?, id: String?): Uri {
        return if(key == null){
            Uri.parse("https://www.themovieapp.com/${scheme}")
        } else {
            Uri.parse("https://www.themovieapp.com/${scheme}/?${key}=$id")
        }
    }

    fun onDynamicLinkClick(
        activity: Activity,
        scheme: String,
        key: String? = null,
        id: String? = null
    ) {
        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(getDeepLink(scheme, key, id)) // getDeepLink로 생성된 딥링크
            .setDynamicLinkDomain("themovieapp.page.link") // 동적 링크 도메인으로, 최종 다이나믹 링크 주소를 만들 때 앞부분에 들어감
            .setAndroidParameters( // 패키지명을 담는 빌더
                DynamicLink.AndroidParameters.Builder(activity.packageName)
                    .setMinimumVersion(1) // 설정한 버전 아래의 앱을 사용 중이라면, 설치가 되어 있어도 앱을 실행시키지 않고 구글 플레이스토어 설치 화면으로 이동
                    .build()
            )
            .buildShortDynamicLink() // 짧은 동적 링크로 생성
            .addOnCompleteListener( // 성공했을 때 처리해줄 로직을 작성
                activity
            ) { task ->
                if (task.isSuccessful) {
                    val shortLink: Uri = task.result.shortLink!!
                    try {
                        val sendIntent = Intent()
                        sendIntent.action = Intent.ACTION_SEND
                        sendIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString())
                        sendIntent.type = "text/plain"
                        activity.startActivity(Intent.createChooser(sendIntent, "Share"))
                    } catch (ignored: ActivityNotFoundException) {
                    }
                } else {
                    Log.i("TAG", task.toString())
                }
            }
    }
}