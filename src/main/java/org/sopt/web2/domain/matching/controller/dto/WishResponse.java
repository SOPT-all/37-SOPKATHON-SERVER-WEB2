package org.sopt.web2.domain.matching.controller.dto;

public record WishResponse(
	Long wishId,
	String location,
	String timeSlot
) {
	public static WishResponse of(Long wishId, String location, String timeSlot) {
		return new WishResponse(wishId, location, timeSlot);
	}
}
