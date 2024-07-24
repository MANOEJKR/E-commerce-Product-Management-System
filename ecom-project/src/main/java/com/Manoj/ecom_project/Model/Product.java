package com.Manoj.ecom_project.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.reflect.DeclareAnnotation;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
//    private static final DeclareAnnotation.Kind Generation = ;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;

 //   @DateTimeFormat(pattern = "yyyy-MM-dd")
   // @JsonFormat(shape = JsonFormat.Shape.STRING , pattern="dd-MM-yyyy")
    private Date releaseDate;
    private boolean productAvailable;
    private int stockQuantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageeDate;

    public void setImageName(String name)
    {
        this.imageName = name;
    }

    public void setImageType(String type)
    {
        this.imageType = type;
    }

    public void setImageDate(byte[] imageeDate)
    {
        this.imageeDate  = imageeDate;
    }

    public byte[] getImageDate() {
        return imageeDate;
    }

    public String getImageType() {
        return imageType;
    }
}
