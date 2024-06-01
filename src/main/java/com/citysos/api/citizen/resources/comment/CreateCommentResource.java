package com.citysos.api.citizen.resources.comment;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateCommentResource {

    @NotNull
    private String content;
    @NotNull
    private Integer givenCitizen;
    @NotNull
    private Integer givenNew;
}
