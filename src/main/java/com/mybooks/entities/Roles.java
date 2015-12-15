package com.mybooks.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
@SequenceGenerator(name = "SEQ_ROLES")
public class Roles extends AuditableEntity implements BaseEntity, Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ROLES")
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	private Long roleId;
	
	@Column(name = "ROLE_NAME", length = 35, unique = true)
	private String roleName;
	
	@ManyToMany(mappedBy = "listOfRoles")
	private List<UserMaster> listOfUser;

	public Roles() {
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserMaster> getListOfUser() {
		return listOfUser;
	}

	public void setListOfUser(List<UserMaster> listOfUser) {
		this.listOfUser = listOfUser;
	}

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}
