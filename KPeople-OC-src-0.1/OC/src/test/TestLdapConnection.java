package test;

import java.util.Enumeration;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

/** Classe di test per LDAP.
 * Viene testata la connessione e l'interrogazione del server
 * @author dellanna
 *
 */
public class TestLdapConnection {
    /** main. */
    public static void main(final String[] args) throws Exception {

//        INITIAL_CONTEXT_FACTORY - driver di connessione al server
//        PROVIDER_URL - url del servizio
//        SECURITY_PRINCIPAL - username dell'utente manager corrispondente al
//                                rootdn definito nel file di configurazione
//        SECURITY_CREDENTIALS - password dell'utente manager corrispondente al
//                                rootpw definito nel file di configurazione

        Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
//        env.put(Context.PROVIDER_URL,
//                "ldap://192.168.0.93:389");
//        env.put(Context.SECURITY_PRINCIPAL,
//                "cn=admin, dc=kpeople, dc=webscience, dc=it");
//        env.put(Context.SECURITY_CREDENTIALS,
//                "admin");
        env.put(Context.PROVIDER_URL, "ldap://192.168.0.1:389");
        env.put(Context.SECURITY_PRINCIPAL,
                "WS\\dellanna");
        env.put(Context.SECURITY_CREDENTIALS, "dG534DTP");
        try {
            DirContext ctx = new InitialDirContext(env);

            SearchControls ctls = new SearchControls();
            ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            String query = "(&(cn={0}*))";
            String argument1 = "Fabio Dell";
            NamingEnumeration<SearchResult> answer = ctx.search(
                    "ou=SBSUsers, ou=Users, ou=MyBusiness,dc=ws, dc=local",
                    query,
                    new Object[]{argument1},
                    ctls);

            while (answer.hasMoreElements()) {
              SearchResult a = answer.nextElement();
              System.out.println(a.getNameInNamespace());

              Attributes result = a.getAttributes();

              
        
                for (NamingEnumeration vals = result.getAll(); vals.hasMoreElements(); System.out.println("\t" + vals.nextElement()));
                
       
              
              
              if (result == null) {
                  System.out.print("Attributi non presenti");
              }else{
                Attribute attr = result.get("cn");
                if (attr != null){
                  System.out.println("cn:");
                  for (NamingEnumeration vals = attr.getAll(); vals.hasMoreElements(); System.out.println("\t" + vals.nextElement()));
                }

                attr = result.get("sn");
                if (attr != null){
                  System.out.println("sn:");
                  for (NamingEnumeration vals = attr.getAll(); vals.hasMoreElements(); System.out.println("\t" + vals.nextElement()));
                }

                attr = result.get("displayName");
                if (attr != null){
                  System.out.println("displayName:");
                  for (NamingEnumeration vals = attr.getAll(); vals.hasMoreElements(); System.out.println("\t" + vals.nextElement()));
                }
                
                attr = result.get("uid");
                if (attr != null){
                  System.out.println("uid:");
                  for (NamingEnumeration vals = attr.getAll(); vals.hasMoreElements(); System.out.println("\t" + vals.nextElement()));
                }

                attr = result.get("userPassword");
                if (attr != null){
                  System.out.println("userPassword:");
                  for (NamingEnumeration vals = attr.getAll(); vals.hasMoreElements(); System.out.println("\t" + vals.nextElement()));
                }
              }
            }
          }
          catch(NamingException ne){
            ne.printStackTrace();
          } 
    }
}
