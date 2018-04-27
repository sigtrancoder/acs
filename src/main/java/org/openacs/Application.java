/**
 *  (C) 2013-2014 Stephan Rauh http://www.beyondjava.net
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.openacs;

import javax.faces.webapp.FacesServlet;
import org.openacs.ACSServlet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.openacs.utils.Subnet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages={"de.beyondjava","org.openacs", "org.openacs.repository"})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

    private ArrayList<Subnet> NoNATNet = new ArrayList<Subnet>();
    private String NoNATNetString = "";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        //FacesServlet servlet = new FacesServlet();
        //ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.jsf");
    	ACSServlet servlet = new ACSServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "/acs");
		return servletRegistrationBean;
    }

    public static String getOverrideServerName() {
        return "";
    }

    public static String getFirmwarePath() {
    	return "";
    }
 
    public static boolean IsNoNATNetwork(String addr) throws UnknownHostException {
        return Application.IsNoNATNetwork(InetAddress.getByName(addr));
    }

    public static boolean IsNoNATNetwork(InetAddress addr) {
        /*for (Subnet s : app.NoNATNet) {
            if (s.isInSubnet(addr)) {
                return true;
            }
        }*/
        return false;
    }
}