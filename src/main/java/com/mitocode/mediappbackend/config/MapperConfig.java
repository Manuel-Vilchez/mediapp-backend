package com.mitocode.mediappbackend.config;

import com.mitocode.mediappbackend.dto.ConsultDTO;
import com.mitocode.mediappbackend.dto.MedicDTO;
import com.mitocode.mediappbackend.model.Consult;
import com.mitocode.mediappbackend.model.Medic;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean(name = "defaultMapper")
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @Bean(name = "medicMapper")
    public ModelMapper medicMapper() {

        ModelMapper modelMapper = new ModelMapper();

        //Escritura
        modelMapper.createTypeMap(MedicDTO.class, Medic.class)
                .addMapping(MedicDTO::getPrimaryName, (dest, v) -> dest.setFirstName((String) v))
                .addMapping(MedicDTO::getSurname, (dest, v) -> dest.setLastName((String) v))
                .addMapping(MedicDTO::getPhoto, (dest, v) -> dest.setPhotoUrl((String) v));

        //Lectura
        modelMapper.createTypeMap(Medic.class, MedicDTO.class)
                .addMapping(Medic::getFirstName, (dest, v) -> dest.setPrimaryName((String) v))
                .addMapping(Medic::getLastName, (dest, v) -> dest.setSurname((String) v));

        return modelMapper;
    }

    @Bean(name = "consultMapper")
    public ModelMapper consultMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Consult.class, ConsultDTO.class)
                .addMapping(e -> e.getMedic().getFirstName(), (dest, v) -> dest.getMedic().setPrimaryName((String) v))
                .addMapping(e -> e.getMedic().getLastName(), (dest, v) -> dest.getMedic().setSurname((String) v));

        return modelMapper;
    }
}
