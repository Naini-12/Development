package com.example.MultiOperation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name = "MOBILE_OTP")
public class MobileOtp {

    @Id
    @Column(name="OTP_ID")
    private Long  otpId;

    @Column(name="OTP_KEY")
    private Long otpKey;

    @Column(name="OTP_VALUE")
    private Long otpValue;

    @Column(name="GENERATED_TIME")
    private Date generatedTime;

    public Long getOtpId() {
        return otpId;
    }

    public void setOtpId(Long otpId) {
        this.otpId = otpId;
    }

    public Long getOtpKey() {
        return otpKey;
    }

    public void setOtpKey(Long otpKey) {
        this.otpKey = otpKey;
    }

    public Long getOtpValue() {
        return otpValue;
    }

    public void setOtpValue(Long otpValue) {
        this.otpValue = otpValue;
    }

    public Date getGeneratedTime() {
        return generatedTime;
    }

    public void setGeneratedTime(Date generatedTime) {
        this.generatedTime = generatedTime;
    }
}
