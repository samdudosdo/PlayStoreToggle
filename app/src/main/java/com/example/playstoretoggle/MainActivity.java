package com.example.playstoretoggle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    // Play Store + dependency Google.
    // Catatan: com.google.android.packageinstaller TIDAK dimasukkan
    // agar Anda masih bisa install APK setelah disable.
    private static final String[] PACKAGES = {
            "com.android.vending",                          // Play Store
            "com.google.android.gms",                       // Google Play Services
            "com.google.android.gsf",                       // Google Services Framework
            "com.google.android.gsf.login",                 // GSF Login (device lama)
            "com.google.android.backuptransport",           // Google Backup
            "com.google.android.syncadapters.contacts",     // Google Contacts Sync
            "com.google.android.syncadapters.calendar",     // Google Calendar Sync
            "com.google.android.onetimeinitializer",        // One Time Initializer
            "com.google.android.configupdater",             // Config Updater
            "com.google.android.partnersetup",              // Partner Setup
            "com.google.android.setupwizard",               // Setup Wizard
            "com.google.android.apps.restore",              // Restore
            "com.google.android.ext.services",              // Ext Services
            "com.google.android.ext.shared",                // Ext Shared
            "com.google.android.gms.policy_sidecar_aps"     // GMS Sidecar
    };

    private TextView logView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scroll = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 32, 32, 32);

        TextView title = new TextView(this);
        title.setText("Play Store + Google Dependencies Toggle\n(butuh root)");
        title.setTextSize(16);
        title.setPadding(0, 0, 0, 24);
        layout.addView(title);

        Button btnDisable = new Button(this);
        btnDisable.setText("DISABLE Play Store + Dependencies");
        btnDisable.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { runCommandAll("disable-user"); }
        });

        Button btnEnable = new Button(this);
        btnEnable.setText("ENABLE Play Store + Dependencies");
        btnEnable.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { runCommandAll("enable"); }
        });

        Button btnStop = new Button(this);
        btnStop.setText("FORCE STOP GMS + Play Store");
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { forceStopAll(); }
        });

        Button btnStatus = new Button(this);
        btnStatus.setText("CHECK STATUS");
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { checkStatus(); }
        });

        layout.addView(btnDisable);
        layout.addView(btnEnable);
        layout.addView(btnStop);
        layout.addView(btnStatus);

        logView = new TextView(this);
        logView.setTextSize(11);
        logView.setPadding(0, 24, 0, 0);
        logView.setTextIsSelectable(true);
        layout.addView(logView);

        scroll.addView(layout);
        setContentView(scroll);
    }

    private void runCommandAll(String action) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(action.toUpperCase()).append(" ===\n");
        for (String pkg : PACKAGES) {
            String cmd = "pm " + action + " " + pkg;
            sb.append("$ ").append(cmd).append("\n");
            sb.append(execRoot(cmd)).append("\n");
        }
        logView.setText(sb.toString());
        Toast.makeText(this, action + " selesai", Toast.LENGTH_SHORT).show();
    }

    private void forceStopAll() {
        String[] cmds = {
                "am force-stop com.google.android.gms",
                "am force-stop com.android.vending",
                "am force-stop com.google.android.gsf",
                "am force-stop com.google.android.gms.ui",
                "am force-stop com.google.android.gms.unstable"
        };
        StringBuilder sb = new StringBuilder();
        sb.append("=== FORCE STOP ===\n");
        for (String cmd : cmds) {
            sb.append("$ ").append(cmd).append("\n");
            sb.append(execRoot(cmd)).append("\n");
        }
        logView.setText(sb.toString());
        Toast.makeText(this, "Force stop selesai", Toast.LENGTH_SHORT).show();
    }

    private void checkStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== STATUS ===\n");
        for (String pkg : PACKAGES) {
            String cmd = "pm list packages -d | grep " + pkg;
            String res = execRoot(cmd);
            boolean disabled = res.contains(pkg);
            sb.append(disabled ? "[DISABLED] " : "[ENABLED ] ").append(pkg).append("\n");
        }
        logView.setText(sb.toString());
    }

    private String execRoot(String command) {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"su", "-c", command});
            BufferedReader out = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = out.readLine()) != null) sb.append(line).append("\n");
            while ((line = err.readLine()) != null) sb.append("ERR: ").append(line).append("\n");
            p.waitFor();
            return sb.length() == 0 ? "(ok)\n" : sb.toString();
        } catch (Exception e) {
            return "ERROR: " + e.getMessage() + "\n";
        }
    }
}