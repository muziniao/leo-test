package org.leo.ssl;

import java.security.Provider;
import java.security.Security;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

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
}
