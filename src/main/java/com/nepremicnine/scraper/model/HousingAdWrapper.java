package com.nepremicnine.scraper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HousingAdWrapper implements Serializable {

    private List<HousingAd> housingAd;

}
