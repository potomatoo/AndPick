<template>
  <div class="service-des col-5 offset-1 mt-5">
    <h1 class="title1 mt-5" style="font-family: 'Do Hyeon', sans-serif;">
      <b>지금 당장</b>
    </h1>
    <h1 class="title2" style="font-family: 'Do Hyeon', sans-serif;">
      <font color="#ecc19c" style="font-family: 'Do Hyeon', sans-serif;"
        >&PICK하라.</font
      >
    </h1>
    <p class="main-description mt-3">
      이제 필요한 뉴스 정보를 스크랩해서 보관하세요. 관심있는 기사를 나만의 글로
      정리해서 보관할 수 있는 서비스를 제공합니다.
    </p>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.service-des {
  overflow: scroll;
}

.title1 {
  font-size: 70px;
}
.title2 {
  font-size: 55px;
  color: #ecc19c;
}
.main-description {
  opacity: 0.6;
  font-weight: 600;
  font-size: 20px;
  color: black !important;
}
</style>
