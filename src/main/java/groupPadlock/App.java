package groupPadlock;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 */
public class App {


    final static Logger logger = Logger.getLogger (App.class);
    public static int volume = 0;
    static java.util.HashMap<String, String> loginSalt = new java.util.HashMap<String, String> ();
    static java.util.HashMap<String, String> loginInformation = new java.util.HashMap<String, String> ();
    static java.util.HashMap<String, String> loginNames = new java.util.HashMap<String, String> ();
    static java.util.HashMap<String, String> padlockArguments = new java.util.HashMap<String, String> ();
    static java.util.ArrayList<Access> loginRoles = new java.util.ArrayList<Access> ();

    public static void logDebug(String str) {
        if (logger.isDebugEnabled ()) {
            logger.debug (str);
        }
    }

    public static void logInfo(String str) {
        if (logger.isInfoEnabled ()) {
            logger.info (str);
        }
    }

    public static void logError(String str) {
        logger.error (str);
    }

    public static void logWarn(String str) {
        logger.warn (str);
    }

    public static void logFatal(String str) {
        logger.fatal (str);
    }

    public static java.time.LocalDate padlockString2Data(String s) {
        try {
            if (s == null) throw new java.time.format.DateTimeParseException ("duh", "none", 5);
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern ("uuuu-M-d");
            java.time.LocalDate date1 = java.time.LocalDate.parse (s, formatter);
            logInfo ("Supplied date: " + date1 + " - OK");
            return date1;
        } catch (java.time.format.DateTimeParseException e) {
            logError ("Invalid data information supplied");
            return null;
        }
    }

    public static void fillOutEmpty(Connection conn) throws SQLException {
        Statement st = conn.createStatement ();

        st.execute ("CREATE TABLE IF NOT EXISTS auth (login varchar(255) PRIMARY KEY,name varchar(255),hash varchar(255),salt varchar(255));");
        st.execute ("INSERT INTO auth VALUES ('jdoe','John Doe','" + md5Custom ("12345" + md5Custom ("sup3rpaZZ")) + "','12345')");
        st.execute ("INSERT INTO auth VALUES ('jrow','Jane Row','" + md5Custom ("67890" + md5Custom ("Qweqrty12")) + "','67890')");
        st.execute ("CREATE TABLE IF NOT EXISTS role (id INT PRIMARY KEY auto_increment, login varchar(255),role INT,path varchar(255));");
        st.execute ("INSERT INTO auth VALUES (1,'jdoe',1,'a')");
        st.execute ("INSERT INTO auth VALUES (2,'jdoe',2,'a.b')");
        st.execute ("INSERT INTO auth VALUES (3,'jrow',4,'a.b.c')");
        st.execute ("INSERT INTO auth VALUES (4,'jdoe',4,'a.b.c')");
        st.execute ("CREATE TABLE IF NOT EXISTS acco (id INT PRIMARY KEY auto_increment, login varchar(255),date_start date,date_end date,role INT);");

    }

    public static void showHelp() {
        System.out.println ("Senchurin Alexsey - M135 - Padlock\n"
                + "Used to authorize some random users on a random service. But really, who starts projects with a lock?\n"
                + "\n"
                + "Arguments\n"
                + "   -login string  - Username\n"
                + "   -pass string   - Password\n"
                + "  [-res string  ] - Desired resource. Path to resource must be in A.B.C format\n"
                + "  [-ds/-de date ] - Start/End date in yyyy.m.d\n"
                + "  [-vol integer ] - Some random value \n"
                + "  [-role string ] - Role. Must be either READ, WRITE or EXECUTE\n"
                + "   -h             - This help page\n");
    }

    private static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance ("MD5");
            messageDigest.reset ();
            messageDigest.update (st.getBytes ());
            digest = messageDigest.digest ();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace ();
        }

        BigInteger bigInt = new BigInteger (1, digest);
        String md5Hex = bigInt.toString (16);

        while (md5Hex.length () < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

    public static int padlockAuth(String login, String password) {
        logDebug ("Trying to sign in with : login:" + login + " pass:" + password);

        if (login.equals ("") || login.equals (null)) {
            logError ("Missing username");
            return 1;
        } // Missing login
        else if (password.equals ("") || password.equals (null)) {
            logError ("Missing password");
            return 2;
        } // Missing password
        else if (! loginInformation.containsKey (login)) {
            logError ("No such user exists");
            return 3;
        } // Invalid login
        else if (! loginInformation.get (login).equals (md5Custom (loginSalt.get (login) + md5Custom (password)))) {
            logError ("Invalid password");
            return 4;
        }
        // Invalid password

        logInfo ("Welcome, " + loginNames.get (login));
        return 0;
    }

    public static int padlockAccess(String login, String resource, String role) {
        String[] explode = resource.split ("\\.");
        for (int i = 1; i < explode.length; i++) explode[i] = explode[i - 1] + "." + explode[i];

        int roleInt = 0;

        switch (role.toLowerCase ()) {
            case "read":
                roleInt = 1;
                break;
            case "write":
                roleInt = 2;
                break;
            case "execute":
                roleInt = 4;
                break;
        }

        for (int i = 0; i < loginRoles.size (); i++) {
            String lgn = loginRoles.get (i).login;
            String res = loginRoles.get (i).path;
            int rle = loginRoles.get (i).role;
            for (int j = 0; j < explode.length; j++) {
                if (res.equals (explode[j]) && roleInt == rle && lgn.equals (login)) {
                    logInfo ("Access granted");
                    return 0;
                }
                ;
            }
        }

        if (roleInt == 0) return 2;
        return 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = go (args);
        System.exit (exitCode);
    }

    public static int go(String[] args) throws Exception {
        loginNames.put ("jdoe", "John Doe");
        loginNames.put ("jrow", "Jane Row");
        loginRoles.add (new Access ("jdoe", "a", 1));
        loginRoles.add (new Access ("jdoe", "a.b", 2));
        loginRoles.add (new Access ("jrow", "a.b.c", 4));
        loginRoles.add (new Access ("jdoe", "a.b.c", 4));
        loginSalt.put ("jdoe", "12345");
        loginSalt.put ("jrow", "67890");
        loginInformation.put ("jdoe", md5Custom (loginSalt.get ("jdoe") + md5Custom ("sup3rpaZZ")));
        loginInformation.put ("jrow", md5Custom (loginSalt.get ("jrow") + md5Custom ("Qweqrty12")));

        // Основная программа
        // ШАГ 0: Отсортировать данные

        // Почему я не использую CLI, несмотря на то, что он вроде бы был? Нет времени разбираться в его настройке
        // Да, это грязно, но на самом деле та библиотека сделала бы тоже самое, только с другими костылями, оно работает,
        // мне это нравится и, что самое главное, я не нарушаю условия требований
        boolean showHelpOnce = false;

        String argsFull = "";
        for (int i = 0; i < args.length; i++) argsFull += args[i] + " ";
        logInfo ("Supplied arguments: " + argsFull);

        // Если ничего не произошло, то этот блок спокойно убьет программу, не прогоняя её дальше
        if (args.length <= 0) {
            showHelp ();
            logInfo ("Nothing happened");
            return 0;
            //System.exit(statusCode);
        }

        int combo1 = 0, combo2 = 0, combo3 = 0;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-login":
                    padlockArguments.put ("login", args[i + 1]);
                    logDebug ("Supplied LOGIN field: " + args[i + 1]);
                    i++;
                    combo1 = combo1 | 1;
                    break;
                case "-pass":
                    padlockArguments.put ("pass", args[i + 1]);
                    logDebug ("Supplied PASS field: " + args[i + 1]);
                    i++;
                    combo1 = combo1 | 2;
                    break;
                case "-res":
                    padlockArguments.put ("res", args[i + 1]);
                    logDebug ("Supplied RES field: " + args[i + 1]);
                    i++;
                    combo2 = combo2 | 1;
                    break;
                case "-role":
                    padlockArguments.put ("role", args[i + 1]);
                    logDebug ("Supplied ROLE field: " + args[i + 1]);
                    i++;
                    combo2 = combo2 | 2;
                    break;
                case "-ds":
                    padlockArguments.put ("ds", args[i + 1]);
                    logDebug ("Supplied DATE START field: " + args[i + 1]);
                    i++;
                    combo3 = combo3 | 1;
                    break;
                case "-de":
                    padlockArguments.put ("de", args[i + 1]);
                    logDebug ("Supplied DATE END field: " + args[i + 1]);
                    i++;
                    combo3 = combo3 | 2;
                    break;
                case "-vol":
                    padlockArguments.put ("vol", args[i + 1]);
                    logDebug ("Supplied VOLUME field: " + args[i + 1]);
                    i++;
                    combo3 = combo3 | 4;
                    break;
                default:
                    if (! showHelpOnce) {
                        showHelpOnce = true;
                        showHelp ();
                    }
                    if (! args[i].equals ("-h"))
                        logError ("Unknown field " + args[i] + " . Aborting...\n Nothing happened");
                    return 0;
            }
        }

        if (combo1 != 3 && combo1 > 0) {
            logFatal ("Not enough arguments supplied");
            return 1;
        }
        if (combo2 != 3 && combo2 > 0) {
            logFatal ("Not enough arguments supplied");
            return 3;
        }
        if (combo3 != 7 && combo3 > 0) {
            logFatal ("Not enough arguments supplied");
            return 5;
        }

        // ШАГ 1: Логинимся

        switch (padlockAuth (padlockArguments.get ("login"), padlockArguments.get ("pass"))) {
            case 1:
            case 3:
                logFatal ("Error 1 - Unknown username " + padlockArguments.get ("login"));
                return 1;
            case 2:
            case 4:
                logFatal ("Error 2 - Incorrect password " + padlockArguments.get ("pass"));
                return 2;
        }

        // ШАГ 2: Узнаем данные о ресурсе
        if (padlockArguments.containsKey ("role") && padlockArguments.containsKey ("res"))
            switch (padlockAccess (padlockArguments.get ("login"), padlockArguments.get ("res"), padlockArguments.get ("role"))) {
                case 2:
                    logFatal ("Error 3 - Invalid role supplied");
                    return 3;
                case 1:
                    logFatal ("Error 4 - No access");
                    return 4;
            }
        else logWarn ("No access information, skipping");

        // ШАГ 3: Заполняем данные о дате и объеме
        if (padlockArguments.containsKey ("ds")) {
            if (padlockString2Data (padlockArguments.get ("ds")) == null) {
                logFatal ("Error 5 - Incorrect date supplied");
                return 5;
            }
        } else logWarn ("No start date information, skipping");
        if (padlockArguments.containsKey ("de")) {
            if (padlockString2Data (padlockArguments.get ("ds")) == null) {
                logFatal ("Error 5 - Incorrect date supplied");
                return 5;
            }
        } else logWarn ("No end date information, skipping");
        if (padlockArguments.containsKey ("vol")) {
            try {
                volume = Integer.parseInt (padlockArguments.get ("vol"));
            } catch (NumberFormatException e) {
                logFatal ("Error 5 - Incorrect volume supplied");
                return 5;
            }
        } else logWarn ("No volume information, skipping");

        // ШАГ 4: ???
        {/*https://www.youtube.com/watch?v=tO5sxLapAts*/}

        // ШАГ 5: PROFIT

        return 0;
    }
}
