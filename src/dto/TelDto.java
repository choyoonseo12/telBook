package dto;

public class TelDto extends CommonField {
    private int id;
    private String name;
    private int age;
    private String address;
    private String phone;

    @Override
    public String toString() {
        return "TelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", insertedDate='" + getInsertedDate() + '\'' +
                ", updatedDate='" + getUpdatedDate() +
                '}';
    }

    // String.format()
        // 문자열을 형식에 맞춰서 만드는 함수
        // System.out.println(객체);
        // 자동으로  toString()실행
        // format 기호
        // %d → 정수 (int)
        // %s → 문자열 (String)
        //  \n → 줄바꿈
        // return str;
        // 만들어진 포맷된 문자열을 반환


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
