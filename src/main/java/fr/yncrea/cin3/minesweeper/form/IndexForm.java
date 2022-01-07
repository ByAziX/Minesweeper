package fr.yncrea.cin3.minesweeper.form;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;


public class IndexForm {
    private UUID id;

    @Max(20)
    @Min(2)
    @NotNull
    private Long width;

    @Max(20)
    @Min(2)
    @NotNull
    private Long height;
    @NotNull
    private Long count;

    public IndexForm() {
        this.width = Long.valueOf(15);
        this.height = Long.valueOf(15);
        this.count = Long.valueOf(50);
    }

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