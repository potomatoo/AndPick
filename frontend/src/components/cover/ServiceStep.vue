<template>
  <div class="service-step">
    <div class="p-5">
      <h3 class="d-flex justify-content-center">
        <b>서비스 이용 방법</b>
      </h3>
      <div class="container d-flex justify-content-around">
        <div
          align="center"
          class="step-box"
          style="border:5px solid #979dac"
          @click="smoothScroll(1)"
          @mouseenter="zoomIn"
          @mouseleave="zoomOut"
        >
          <div class="step-text">
            피드
          </div>
          <div class="go-detail">자세히</div>
          <i class="mdi-step mdi mdi-hexagon"></i>
        </div>

        <div
          align="center"
          class="step-box"
          style="border:5px solid #7d8597;"
          @click="smoothScroll(2)"
          @mouseenter="zoomIn"
          @mouseleave="zoomOut"
        >
          <div class="step-text">보드</div>
          <div class="go-detail">자세히</div>
          <i class="mdi-step mdi mdi-checkbox-blank-circle"></i>
        </div>

        <div
          align="center"
          class="step-box"
          style="border:5px solid #5c677d;"
          @click="smoothScroll(3)"
          @mouseenter="zoomIn"
          @mouseleave="zoomOut"
        >
          <div class="step-text">마이페이지</div>
          <div class="go-detail">자세히</div>
          <i class="mdi-step mdi mdi-star"></i>
        </div>
      </div>
    </div>
    <ServiceDetail ref="detail" />
  </div>
</template>

<script>
import ServiceDetail from "./ServiceDetail.vue";
export default {
  name: "ServiceStep",
  components: {
    ServiceDetail
  },
  data() {
    return {};
  },
  methods: {
    zoomIn(event) {
      event.target.style.transform = "scale(1.1)";
      event.target.style.zIndex = 1;
      event.target.style.transition = "all 0.5s";
    },
    zoomOut(event) {
      event.target.style.transform = "scale(1)";
      event.target.style.zIndex = 0;
      event.target.style.transition = "all 0.5s";
    },
    smoothScroll(event) {
      const el = this.$refs.detail.$el;
      if (event === 1) {
        const target = el.querySelector(".feed");
        const targetTop = target.getBoundingClientRect().top;
        const abTop = window.pageYOffset + targetTop;
        window.scrollTo({ top: abTop - 150, behavior: "smooth" });
      } else if (event === 2) {
        const target = el.querySelector(".board");
        const targetTop = target.getBoundingClientRect().top;
        const abTop = window.pageYOffset + targetTop;
        window.scrollTo({ top: abTop - 150, behavior: "smooth" });
      } else {
        const target = el.querySelector(".mypage");
        const targetTop = target.getBoundingClientRect().top;
        const abTop = window.pageYOffset + targetTop;
        window.scrollTo({ top: abTop - 150, behavior: "smooth" });
      }
    }
  }
};
</script>

<style>
.go-detail {
  opacity: 0.5;
}
.step-box {
  cursor: pointer;
  width: 230px;
  height: 230px;
  /* background-color: #17212c; */
  /* color: white; */
  margin: auto;
  border-radius: 25px;
}

.step-text {
  font-size: 25px;
  /* font-weight: 900; */
  margin-top: 50px;
  margin-bottom: 20px;
  /* transition: font-size 2s; */
  font-family: "Black Han Sans", sans-serif;
  /* font-family: "Jua", sans-serif; */
}
.mdi-step {
  font-size: 33px;
}
</style>
