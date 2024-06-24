package ru.project.otus.da_dataservice.model.response_model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private String result;
    private String country;
    private String house;
    private String flat;
    private List<Metro> metro;
    private String kpp;
    private Capital capital;
    private String invalid;
    private Management management;
    private String founders;
    private Manager manager;
    private String predecessors;
    private String successors;
    private String source;
    private String qc;
    private String hid;
    private String type;
    private State state;
    private OrganizationForm opf;
    private String inn;
    private String ogrn;
    private String okpo;
    private String okato;
    private String oktmo;
    private String bic;
    private String swift;
    private String okogu;
    private String okfs;
    private List<Activity> okveds;
    private List<Phones> phones;
    private List<Emails> emails;
    private Email email;
    private Company company;
    private String code;
    private Name name;
    private Address address;
    private String phone;
    private String comment;
    private String osf;
    private String fax;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("city_area")
    private String cityArea;

    @JsonProperty("city_district")
    private String cityDistrict;

    @JsonProperty("house_cadnum")
    private String houseCadNum;

    @JsonProperty("flat_cadnum")
    private String flatCadNum;

    @JsonProperty("geo_lat")
    private String latitude;

    @JsonProperty("geo_lon")
    private String longitude;

    @JsonProperty("qc_geo")
    private String qcGeo;

    @JsonProperty("region_kladr_id")
    private String regionKladrId;

    @JsonProperty("region_fias_id")
    private String regionFiasId;

    @JsonProperty("boxberry_id")
    private String boxBerryId;

    @JsonProperty("cdek_id")
    private String cdekId;

    @JsonProperty("dpd_id")
    private String dpdId;

    @JsonProperty("is_closed")
    private String isClosed;

    @JsonProperty("type_code")
    private String typeCode;

    @JsonProperty("address_str")
    private String addressStr;

    @JsonProperty("address_kladr_id")
    private String addressKladrId;

    @JsonProperty("address_qc")
    private String addressQC;

    @JsonProperty("schedule_mon")
    private String scheduleMon;

    @JsonProperty("schedule_tue")
    private String scheduleTue;

    @JsonProperty("schedule_wed")
    private String scheduleWed;

    @JsonProperty("schedule_thu")
    private String scheduleThu;

    @JsonProperty("schedule_fri")
    private String scheduleFri;

    @JsonProperty("schedule_sat")
    private String scheduleSat;

    @JsonProperty("schedule_sun")
    private String scheduleSun;

    @JsonProperty("branch_type")
    private String branchType;

    @JsonProperty("branch_count")
    private String branchCount;

    @JsonProperty("correspondent_account")
    private String correspondentAccount;

    @JsonProperty("registration_number")
    private String registrationNumber;

    @JsonProperty("payment_city")
    private String paymentCity;

    @JsonProperty("ogrn_date")
    private String ogrnDate;

    @JsonProperty("okved_type")
    private String okvedType;

    @JsonProperty("employee_count")
    private String employeeCount;

    @JsonProperty("name_short")
    private String nameShort;

    @JsonProperty("payment_name")
    private String paymentName;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("bank_bic")
    private String bankBic;

    @JsonProperty("bank_correspondent_account")
    private String bankCorrespondentAccount;

    @JsonProperty("bank_account")
    private String bankAccount;
    
    @JsonProperty("parent_code")
    private String parentCode;

    @JsonProperty("parent_name")
    private String parentName;

    @JsonProperty("parent_address")
    private String parentAddress;
    
    @JsonProperty("parent_phone")
    private String parentPhone;

    @JsonProperty("parent_comment")
    private String parentComment;

    @JsonProperty("class")
    private String goodsClass;

    @JsonProperty("number")
    private String number;

    @JsonProperty("name_ru")
    private String nameRu;

    @JsonProperty("name_en")
    private String nameEn;

    @JsonProperty("name_fr")
    private String nameFr;

    @JsonProperty("strcode")
    private String strСode;
}