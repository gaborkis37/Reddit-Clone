package com.homeProj.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.homeProj.domain.validator.PasswordsMatch;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@PasswordsMatch
public class User implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	@Size(min = 8, max = 30)
	@Column(nullable = false, unique = true)
	private String email;

	@NonNull
	@Column(length = 100)
	private String password;

	@NonNull
	@Column(nullable = false)
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles = new HashSet<>();

	@NonNull
	@NotNull(message = "You must enter a first name.")
	private String firstName;

	@NonNull
	@NotNull(message = "You must enter a lastName name.")
	private String lastName;

	@Transient
	@Setter(AccessLevel.NONE)
	private String fullName;

	@NonNull
	@NotNull
	@Column(nullable = false, unique = true)
	private String alias;
	
	@Transient
	@NotEmpty(message = "please enter Password confirmation")
	private String confirmPassword;

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public void addRoles(Set<Role> roles) {
		roles.forEach(this::addRole);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

}
