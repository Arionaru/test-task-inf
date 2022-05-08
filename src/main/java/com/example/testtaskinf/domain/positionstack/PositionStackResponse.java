package com.example.testtaskinf.domain.positionstack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionStackResponse {
    private List<StackData> data;
}
