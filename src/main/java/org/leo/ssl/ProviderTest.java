package org.leo.ssl;

import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

import sun.security.jca.ProviderList;
import sun.security.jca.Providers;

@SuppressWarnings("restriction")
public class ProviderTest {

	@Test
	public void testProvider(){
		Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            Provider provider = providers[i];
            System.out.println("Provider name: " + provider.getName());
            System.out.println("Provider information: " + provider.getInfo());
            System.out.println("Provider version: " + provider.getVersion());
            Set<Entry<Object, Object>> entries = provider.entrySet();
            Iterator<Entry<Object, Object>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                System.out.println("Property entry: " + iterator.next());
            }
            System.out.println("------------------------------------------------------------");
        }
        
	}
	
	//@Test
	public void test2(){
        ProviderList list = Providers.getProviderList();
        List<Provider> providers = list.providers();


        System.out.println("---start---------------------------------------------------------");
        for (Provider provider : providers) {
            System.out.println("Provider Class: " + provider.getClass().getName());
            System.out.println("Provider name: " + provider.getName());
            System.out.println("Provider information: " + provider.getInfo());
            System.out.println("Provider version: " + provider.getVersion());
            Set<Entry<Object, Object>> entries = provider.entrySet();
            Iterator<Entry<Object, Object>> iterator = entries.iterator();
            while (iterator.hasNext()) {
                System.out.println("Property entry: " + iterator.next());
            }
            System.out.println("------------------------------------------------------------");
        }
	}
	
	//@Test
	public void test3(){
		 Provider p = Security.getProvider("SunJCE");
		 Set<Service> set = p.getServices();
		 for(Service s : set){
			 System.out.println(s.getType() + "---" + s.getAlgorithm() + "---" + s.getClassName());
		 }
		 Service s = p.getService("Cipher", "RSA");
		 System.out.println(s);
		 //p.getServices().add(new Service(p, "Signature", s.getAlgorithm(), s.getClassName(), s., attributes));
	}
}
