package com.springMyBatisEFGMart.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springMyBatisEFGMart.domain.Item;


@Mapper
public interface ItemMapper {
	
	List<Item> selectItemList();
}
