package fr.yncrea.cin3.minesweeper.form;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;


public class IndexForm {
    private UUID id;

    @NotBlank
    @Size(min = 10)
    private Long width;

    @NotBlank
    @Size(min = 10)
    private Long height;

    @Min(0)
    private Long count;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}