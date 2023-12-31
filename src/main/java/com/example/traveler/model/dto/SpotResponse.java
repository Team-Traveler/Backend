package com.example.traveler.model.dto;

import com.example.traveler.model.entity.Spot;
import com.example.traveler.model.entity.Travel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SpotResponse {
    int sId;
    int tId;
    int dcId;
    String title;
    Double latitude;
    Double longitude;
}
