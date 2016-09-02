package com.tccv.dao;

import com.tccv.pojo.FileVersion;
import com.tccv.pojo.FileVersionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileVersionMapper {
    int countByExample(FileVersionExample example);

    int deleteByExample(FileVersionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileVersion record);

    int insertSelective(FileVersion record);

    List<FileVersion> selectByExample(FileVersionExample example);

    FileVersion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileVersion record, @Param("example") FileVersionExample example);

    int updateByExample(@Param("record") FileVersion record, @Param("example") FileVersionExample example);

    int updateByPrimaryKeySelective(FileVersion record);

    int updateByPrimaryKey(FileVersion record);
}