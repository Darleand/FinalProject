package by.webproject.service.converter;

import java.util.List;

public interface Converter<Entity, Dto> {
    Entity toEntity(Dto dto);

    List<Entity> toEntity(List<Dto> dtos);

    Dto toDTO(Entity entity);

    List<Dto> toDTO(List<Entity> entities);
}

