package in.sp.main.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class Empinfo implements UserDetails{
	String username=null;
	String password=null;
	
	public Empinfo(Employee employee) {
		username=employee.getUsername();
		password=employee.getPassword();
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
	
		return password;
	}

	@Override
	public String getUsername() {
	
		return username;
	}
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
