package address;

import java.util.Vector;

public class Contact{
    private String name;//姓名
    private String mobilePhone;//手机
    private String email;//电子邮箱
    private String telephone;//固定电话
    private String cornet;//短号
    private String birthday;//生日
    private String workUnit;//工作单位
    private String familyAddress;//家庭地址
    private String postcode;//邮编
    private String instantMessaging;//即时通讯
    private String photo;//相片
    private String personalHomepage;//个人主页
    private String comment;//备注
    private Vector<String> group;//所属组别，一个人可以属于多个组

    public Contact() {
        this("","","","","","","","","","","","","",new Vector());
    }

    public Contact(String name, String mobilePhone, String email, String telephone, String cornet, String birthday, String workUnit, String familyAddress, String postcode, String instantMessaging, String photo, String personalHomepage, String comment, Vector<String> group) {
        this.name = name;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.telephone = telephone;
        this.cornet = cornet;
        this.birthday = birthday;
        this.workUnit = workUnit;
        this.familyAddress = familyAddress;
        this.postcode = postcode;
        this.instantMessaging = instantMessaging;
        this.photo = photo;
        this.personalHomepage = personalHomepage;
        this.comment = comment;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCornet() {
        return cornet;
    }

    public void setCornet(String cornet) {
        this.cornet = cornet;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getInstantMessaging() {
        return instantMessaging;
    }

    public void setInstantMessaging(String instantMessaging) {
        this.instantMessaging = instantMessaging;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPersonalHomepage() {
        return personalHomepage;
    }

    public void setPersonalHomepage(String personalHomepage) {
        this.personalHomepage = personalHomepage;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGroup(Vector<String> group) {
        this.group = group;
    }

    public Vector<String> getGroup() {
        return group;
    }
    
    @Override
    public String toString(){
        return name;
    }
    /**
     * 判断此人是否属于这个组
     * @param groupName
     * @return 
     */
    public boolean isBelongsTo(String groupName){
        return group.contains(groupName);
    }
}
