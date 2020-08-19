<template>
  <div class="service-des col-md-5 offset-md-1 offset-1">
    <div class="service-box">
      <h1 class="title1"><b>나만의</b></h1>
      <h1 class="title2">
        <b><font color="#5cb85c">스크랩</font>서비스</b>
      </h1>
      <p class="main-description mt-3">
        이제 필요한 뉴스기사 및 기술블로그의 정보를 스크랩해서 보관하세요. 저희
        &PICK 서비스는 게시글을 스크랩하여 나만의 글로 정리해서 보관할 수 있는
        서비스를 제공합니다.
      </p>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.service-des {
  display: flex;
  align-items: center;
}
.title1 {
  font-size: 60px;
  color: white;
}
.title2 {
  font-size: 45px;
  color: white;
}
.main-description {
  opacity: 0.6;
  font-weight: 600;
  font-size: 20px;
  color: white;
  word-break: keep-all;
}
</style>
