package com.backbase.assignment.data.models


data class RequestGeneral (
	val results : List<Results>,
	val total_pages : Int,
	val total_results : Int
)