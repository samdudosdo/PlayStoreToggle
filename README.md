# PlayStore Toggle

APK sederhana (butuh **root**) untuk **enable / disable** Play Store & dependency Google-nya dalam satu klik.

## Fitur
- DISABLE Play Store + Google Dependencies
- ENABLE Play Store + Google Dependencies
- FORCE STOP GMS + Play Store (untuk menghentikan `gms.unstable` yang boros CPU)
- CHECK STATUS (lihat package mana yang disabled)

## Cara build APK via GitHub (tanpa Android Studio)
1. Buat repo baru di GitHub, misal `PlayStoreToggle`.
2. Upload **seluruh isi folder** `PlayStoreToggle/` ke repo (branch `main`).
3. Buka tab **Actions** â†’ workflow **Build APK** akan otomatis jalan.
4. Setelah selesai, unduh APK di bagian **Artifacts** â†’ `PlayStoreToggle-debug`.
5. Install APK di device (izinkan install dari sumber tidak dikenal).
6. Buka app â†’ beri akses root saat diminta â†’ tekan tombol yang diinginkan.

## Package yang di-toggle
| Package | Keterangan |
|---|---|
| com.android.vending | Play Store |
| com.google.android.gms | Google Play Services |
| com.google.android.gsf | Google Services Framework |
| com.google.android.gsf.login | GSF Login (device lama) |
| com.google.android.backuptransport | Google Backup |
| com.google.android.syncadapters.contacts | Sinkron kontak Google |
| com.google.android.syncadapters.calendar | Sinkron kalender Google |
| com.google.android.onetimeinitializer | One Time Initializer |
| com.google.android.configupdater | Config Updater |
| com.google.android.partnersetup | Partner Setup |
| com.google.android.setupwizard | Setup Wizard |
| com.google.android.packageinstaller | Package Installer |
| com.google.android.apps.restore | Restore |
| com.google.android.ext.services | Ext Services |
| com.google.android.ext.shared | Ext Shared |
| com.google.android.gms.policy_sidecar_aps | GMS Sidecar |

## âš ï¸ Peringatan
- Disabling `com.google.android.gms` akan **memutus notifikasi Gmail, WhatsApp (jika pakai GCM), Maps, dll.** Gunakan dengan sadar.
- `com.google.android.packageinstaller` sebaiknya **jangan** di-disable permanen â€” Anda tidak akan bisa install APK. Hapus dari list `PACKAGES` di `MainActivity.java` jika perlu.
- App ini butuh **root** (Magisk/SuperSU). Tanpa root, `su` tidak akan jalan.

## Lisensi
MIT