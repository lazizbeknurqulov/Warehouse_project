package uz.oak.project_warehouse.payload.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultDto {
    private List<ProductResponseDto> result;
}
