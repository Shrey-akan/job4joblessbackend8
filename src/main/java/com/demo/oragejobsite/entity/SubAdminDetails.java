package com.demo.oragejobsite.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subadmin")
public class SubAdminDetails {
	 @Id
	    private String subadminid;
	    private String subadminame;
	    private String subadminmail;
	    private String subadminpassword;
	    private boolean manageUsers;
	    private boolean manageEmployers;
	    private boolean postJob;
	    private boolean applyJob;
	    private boolean manageBlogs;
	    private boolean pushNotification;
	    private boolean approveJobDetails;
		public SubAdminDetails() {
			super();
			// TODO Auto-generated constructor stub
		}
		public SubAdminDetails(String subadminid, String subadminame, String subadminmail, String subadminpassword,
				boolean manageUsers, boolean manageEmployers, boolean postJob, boolean applyJob, boolean manageBlogs,
				boolean pushNotification, boolean approveJobDetails) {
			super();
			this.subadminid = subadminid;
			this.subadminame = subadminame;
			this.subadminmail = subadminmail;
			this.subadminpassword = subadminpassword;
			this.manageUsers = manageUsers;
			this.manageEmployers = manageEmployers;
			this.postJob = postJob;
			this.applyJob = applyJob;
			this.manageBlogs = manageBlogs;
			this.pushNotification = pushNotification;
			this.approveJobDetails = approveJobDetails;
		}
		public String getSubadminid() {
			return subadminid;
		}
		public void setSubadminid(String subadminid) {
			this.subadminid = subadminid;
		}
		public String getSubadminame() {
			return subadminame;
		}
		public void setSubadminame(String subadminame) {
			this.subadminame = subadminame;
		}
		public String getSubadminmail() {
			return subadminmail;
		}
		public void setSubadminmail(String subadminmail) {
			this.subadminmail = subadminmail;
		}
		public String getSubadminpassword() {
			return subadminpassword;
		}
		public void setSubadminpassword(String subadminpassword) {
			this.subadminpassword = subadminpassword;
		}
		public boolean isManageUsers() {
			return manageUsers;
		}
		public void setManageUsers(boolean manageUsers) {
			this.manageUsers = manageUsers;
		}
		public boolean isManageEmployers() {
			return manageEmployers;
		}
		public void setManageEmployers(boolean manageEmployers) {
			this.manageEmployers = manageEmployers;
		}
		public boolean isPostJob() {
			return postJob;
		}
		public void setPostJob(boolean postJob) {
			this.postJob = postJob;
		}
		public boolean isApplyJob() {
			return applyJob;
		}
		public void setApplyJob(boolean applyJob) {
			this.applyJob = applyJob;
		}
		public boolean isManageBlogs() {
			return manageBlogs;
		}
		public void setManageBlogs(boolean manageBlogs) {
			this.manageBlogs = manageBlogs;
		}
		public boolean isPushNotification() {
			return pushNotification;
		}
		public void setPushNotification(boolean pushNotification) {
			this.pushNotification = pushNotification;
		}
		public boolean isApproveJobDetails() {
			return approveJobDetails;
		}
		public void setApproveJobDetails(boolean approveJobDetails) {
			this.approveJobDetails = approveJobDetails;
		}
	    
	    
	    
}
