<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="full-width-div text-white banner">
	<div id="carouselExample" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active" data-bs-interval="4000">
				<img
					src="${pageContext.request.contextPath}/resources/images/banner01.png"
					class="d-block w-100" alt="..." />
			</div>
			<div class="carousel-item" data-bs-interval="4000">
				<img
					src="${pageContext.request.contextPath}/resources/images/banner02.png"
					class="d-block w-100" alt="..." />
			</div>
			<div class="carousel-item" data-bs-interval="4000">
				<img
					src="${pageContext.request.contextPath}/resources/images/banner03.png"
					class="d-block w-100" alt="..." />
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExample" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExample" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
</div>