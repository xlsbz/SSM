package com.dhr.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dhr.store.domain.Product;
import com.dhr.store.domain.ProductExample;

public interface ProductMapper {
	int countByExample(ProductExample example);

	int deleteByExample(ProductExample example);

	int deleteByPrimaryKey(String pid);

	int insert(Product record);

	int insertSelective(Product record);

	List<Product> selectByExample(ProductExample example);

	Product selectByPrimaryKey(String pid);

	int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

	int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

	int updateByPrimaryKeySelective(Product record);

	int updateByPrimaryKey(Product record);

	// *******************************自定义*****************************************//
	List<Product> findLikeProduct(int end);
}