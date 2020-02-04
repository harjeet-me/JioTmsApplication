package com.jio.tms.v1.service.mapper;

import com.jio.tms.v1.domain.*;
import com.jio.tms.v1.service.dto.FileSystemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileSystem} and its DTO {@link FileSystemDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmailMapper.class})
public interface FileSystemMapper extends EntityMapper<FileSystemDTO, FileSystem> {

    @Mapping(source = "email.id", target = "emailId")
    FileSystemDTO toDto(FileSystem fileSystem);

    @Mapping(source = "emailId", target = "email")
    FileSystem toEntity(FileSystemDTO fileSystemDTO);

    default FileSystem fromId(Long id) {
        if (id == null) {
            return null;
        }
        FileSystem fileSystem = new FileSystem();
        fileSystem.setId(id);
        return fileSystem;
    }
}
