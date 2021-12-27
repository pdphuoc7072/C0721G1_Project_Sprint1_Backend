package com.codegym.dto;

import com.codegym.model.Producer;
import com.codegym.model.Supplies;
import com.codegym.model.SuppliesType;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SuppliesDTO implements Validator {

    private Long id;
    @NotBlank(message = "Không được để trống.")
    @Pattern(regexp = "^(MVT-)(\\d{4})$", message = "Phải đúng định dạng MVT-xxxx")
    private String code;

    @NotBlank(message = "Không được để trống")
    @Size(min = 5,max = 50)
    private String name;

    @NotNull(message = "Không được để trống")
    @Min(1000)
    private Long price;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^(?:19\\d{2}|20\\d{2})[-/.](?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])$",
            message = "Phải đúng định dạng: dd/MM/yyyy.")
    private String productionDate;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^(?:19\\d{2}|20\\d{2})[-/.](?:0[1-9]|1[012])[-/.](?:0[1-9]|[12][0-9]|3[01])$",
            message = "Phải đúng định dạng: dd/MM/yyyy.")
    private String expiryDate;

    @NotBlank(message = "Không được để trống")
    private String introduce;

    @NotBlank(message = "Không được để trống")
    private String technicalInformation;

    @NotBlank(message = "Không được để trống")
    private String image;

    private SuppliesType suppliesType;
    private Producer producer;


    public SuppliesDTO() {
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public SuppliesType getSuppliesType() {
        return suppliesType;
    }

    public void setSuppliesType() {
        this.suppliesType = suppliesType;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = this.producer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = price;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTechnicalInformation() {
        return technicalInformation;
    }

    public void setTechnicalInformation(String technicalInformation) {
        this.technicalInformation = technicalInformation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    List<Supplies> suppliesList = new ArrayList<>();

    public List<Supplies> getSuppliesList() {
        return suppliesList;
    }

    public void setSuppliesList(List<Supplies> suppliesList) {
        this.suppliesList = suppliesList;
    }

    private boolean checkCode;


    public boolean isCheckCode() {
        return checkCode;
    }

    public void setCheckCode(boolean checkCode) {
        this.checkCode = checkCode;
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SuppliesDTO suppliesDTO = (SuppliesDTO) target;
        for (Supplies supplies: suppliesList){
            if (suppliesDTO.checkCode){
                if (supplies.getCode().equals(suppliesDTO.getCode())){
                    errors.rejectValue("code","code.equals","Mã đã tồn tại");
                }
            }

        };
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date start= sdf.parse(suppliesDTO.productionDate);
            Date end = sdf.parse(suppliesDTO.expiryDate);
            Date now = sdf.parse(String.valueOf(LocalDate.now()));
            if(start.compareTo(now)<=0){
                errors.rejectValue("productionDate", "SDF", "Ngày sản xuất phải nhỏ hơn hoặc bàng ngày bây giờ");
            }
            if(end.compareTo(now)<=0){
                errors.rejectValue("expiryDate", "EDF", "Hạn sử dụng phải lớn hơn ngày bây giờ!");
            }
            if(end.compareTo(start)<=0){
                errors.rejectValue("productionDate", "SDM", "Ngày sản xuất phải trước ngày hết hạn !");
                errors.rejectValue("expiryDate", "EDM", "Ngày hết hạn phải sau ngày sản xuất !");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
