/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import cafe.management.system.CafeManagementSystem;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.DbOperations;
import dao.UserDao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.TableModel;

/**
 *
 * @author kingr
 */
public class UpdateChecker {

    static JDialog dialogLoading = new JDialog();
    static URL url = UpdateChecker.class.getResource("/images/Download in Process.gif");
    static ImageIcon icon = new ImageIcon(url);
    private static int compareVersion(String currentVersion, String latestVersion) {
        String[] currentVersionParts = currentVersion.split("\\.");
        String[] latestVersionParts = latestVersion.split("\\.");
        int length = Math.max(currentVersionParts.length, latestVersionParts.length);
        for (int i = 0; i < length; i++) {
            int currentPart = (i < currentVersionParts.length) ? Integer.parseInt(currentVersionParts[i]) : 0;
            int latestPart = (i < latestVersionParts.length) ? Integer.parseInt(latestVersionParts[i]) : 0;
            if (currentPart < latestPart) {
                return -1;
            } else if (currentPart > latestPart) {
                return 1;
            }
        }
        return 0;
    }

    public static String getLatestVersion() {
        String EXE_DOWNLOAD_URL = "";
        String latest_version = "";
        try {
            URL url = new URL("https://api.github.com/repos/kingrishabdugar/Green-Leaf-Gourmet/releases/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/vnd.github+json");
            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                latest_version = response.toString().split("\"tag_name\":\"v")[1].split("\"")[0];

                JsonElement json = new JsonParser().parse(response.toString());
                JsonArray assets = json.getAsJsonObject().get("assets").getAsJsonArray();
                for (JsonElement asset : assets) {
                    JsonObject assetObj = asset.getAsJsonObject();
                    if (assetObj.get("name").getAsString().endsWith(".exe")) {
                        EXE_DOWNLOAD_URL = assetObj.get("browser_download_url").getAsString();
                    }
                }
                //String query = "UPDATE updateapp SET EXE_URL = "+ + "," + "latest_version = "+latest_version ;
                String query = "update updateapp set EXE_URL='" + EXE_DOWNLOAD_URL + "',latest_version = '" + latest_version + "' where id='1" + "'";
                DbOperations.setDataorDelete(query, "");
                return latest_version;
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static void downloadAndInstallUpdate(String exeUrl) {
        try {
            URL website = new URL(exeUrl);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            //JOptionPane.showMessageDialog(null, "<html><b style=\"color:Green\">Updates will be downloaded ⬇ & Installed in the background ✔ ❗</b></html>" + "\n" + "<html><b style=\"color:Green\">Meanwhile you can relax ☺ & continue with your work 💻</b></html>");

            JOptionPane.showMessageDialog(null, "<html><b style=\"color:Green\">Updates will be downloaded ⬇ & Installed in the background ✔ ❗</b></html>" + "\n" + "<html><b style=\"color:Green\">Meanwhile you can relax ☺ & continue with your work 💻</b></html>", "Update", JOptionPane.PLAIN_MESSAGE, icon);

            FileOutputStream fos = new FileOutputStream(new File("Green-Leaf-Gourmet_update.exe"));
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            // Install the update
            Runtime.getRuntime().exec("Green-Leaf-Gourmet_update.exe");
            String current_version = getLatestVersion();
            String query = "update updateapp set current_version='" + current_version + "' where id='1" + "'";
            DbOperations.setDataorDelete(query, "");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "<html><b style=\"color:Red\">Failed ❌ to check for updates  ❗ </b></html>");
        }
    }

    public static void checkforupdates() {

        CafeManagementSystem.createDialog(dialogLoading, "/images/Loading GIFs/Burger.gif");
        //dialogLoading.setAlwaysOnTop(false);
        SwingWorker<Void, Void> worker;
        worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                String latestVersion = getLatestVersion();
                String currentVersion = "";
                String EXE_URL = "";

                try {
                    ResultSet rs = DbOperations.getData("select * from updateapp where id = '1'");
                    while (rs.next()) {
                        currentVersion = rs.getString("current_version");
                        EXE_URL = rs.getString("EXE_URL");
                    }
                } catch (SQLException ex) {
                    dialogLoading.dispose();
                    JOptionPane.showMessageDialog(null, "<html><b style=\"color:Red\">Failed ❌ to check for updates  ❗ </b></html>");
                    dialogLoading.dispose();
                }

                if (latestVersion == null) {
                    dialogLoading.dispose();
                    JOptionPane.showMessageDialog(null, "<html><b style=\"color:Red\">Failed ❌ to check for updates  ❗ </b></html>");
                    dialogLoading.dispose();
                } else if (latestVersion.compareTo(currentVersion) > 0) {
                    dialogLoading.dispose();
                    int a = JOptionPane.showConfirmDialog(null, "<html><b style=\"color:Green\">Updates are available ❗</b></html>" + "\n" + "<html><b style=\"color:Green\">Click YES ✔ to proceed downloading in the background ❗</b></html>", "Download Latest Update!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
                    if (a == 0) {
                        downloadAndInstallUpdate(EXE_URL);
                    }

                } else {
                    dialogLoading.dispose();
                    int a = JOptionPane.showConfirmDialog(null, "<html><b style=\"color:Green\">Your App is Up-To-Date ✔❗</b></html>" + "\n" + "<html><b style=\"color:Green\">If you want to repair the installation, proceed to click YES ✔ </b></html>", "Repair Installation!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
                    if (a == 0) {
                        downloadAndInstallUpdate(EXE_URL);
                    }
                    dialogLoading.dispose();
                }
                dialogLoading.dispose();
                return null;

            }

            @Override
            protected void done() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        dialogLoading.dispose();
                    }
                });
            }
        };
        worker.execute();
    }
}
