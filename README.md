# PlayStore Toggle

APK sederhana (butuh **root**) untuk **enable / disable** Play Store & dependency Google-nya.

## Fitur
- DISABLE Play Store + Google Dependencies
- ENABLE Play Store + Google Dependencies
- FORCE STOP GMS + Play Store (untuk menghentikan `gms.unstable` yang boros CPU)
- CHECK STATUS

## Cara build APK via GitHub
1. Push folder ini ke repo GitHub (branch `main`).
2. Tab **Actions** -> workflow **Build APK** otomatis jalan.
3. Unduh **Artifacts** -> `PlayStoreToggle-debug`.
4. Extract, install `app-debug.apk` di HP root.

## Package yang di-toggle
- com.android.vending
- com.google.android.gms
- com.google.android.gsf
- com.google.android.gsf.login
- com.google.android.backuptransport
- com.google.android.syncadapters.contacts
- com.google.android.syncadapters.calendar
- com.google.android.onetimeinitializer
- com.google.android.configupdater
- com.google.android.partnersetup
- com.google.android.setupwizard
- com.google.android.apps.restore
- com.google.android.ext.services
- com.google.android.ext.shared
- com.google.android.gms.policy_sidecar_aps

> `com.google.android.packageinstaller` **tidak** dimasukkan agar Anda masih bisa install APK.

## Peringatan
- Disabling `com.google.android.gms` akan memutus notifikasi Gmail, WhatsApp, Maps, dll.
- Butuh root (Magisk/SuperSU).

## Lisensi
MIT