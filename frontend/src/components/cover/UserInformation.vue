<template>
  <div class="user-out">
    <transition name="slide-user">
      <div v-if="showUser">
        <h3 class="d-flex justify-content-center">
          <b>우리의 사용자들</b>
        </h3>
      </div>
    </transition>
    <div class="row mx-5 px-5">
      <div class="col-lg-4" align="center">
        <transition name="slide-user">
          <div v-if="showUser">
            <img
              src="@/assets/user_jobseeker.png"
              height="auto"
              width="250px"
            />
          </div>
        </transition>
        <transition name="slide-user">
          <div v-if="showUser">
            <div style="width:250px">
              <h5 class="description d-flex justify-content-center">
                취준생
              </h5>
              <p>
                취업준비생들의 면접준비에 유용한 정보를 제공합니다
              </p>
            </div>
          </div>
        </transition>
      </div>
      <div class="col-lg-4" align="center">
        <transition name="slide-user">
          <div v-if="showUser">
            <img src="@/assets/user_industry.png" height="auto" width="250px" />
          </div>
        </transition>
        <transition name="slide-user">
          <div v-if="showUser">
            <div style="width:250px">
              <h5 class="description d-flex justify-content-center">
                개발자
              </h5>
              <p>다양한 기술블로그를 통해 새로운 기술을 습득해보세요</p>
            </div>
          </div>
        </transition>
      </div>
      <div class="col-lg-4" align="center">
        <transition name="slide-user">
          <div v-if="showUser">
            <img src="@/assets/user_business.png" height="auto" width="250px" />
          </div>
        </transition>
        <transition name="slide-user">
          <div v-if="showUser">
            <div style="width:250px">
              <h5 class="description d-flex justify-content-center">
                기타
              </h5>
              <p>
                최신 산업 트렌드에 관한 기사를 읽어보세요
              </p>
            </div>
          </div>
        </transition>
      </div>
    </div>
  </div>
</template>

<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
export default {
  name: "UserInformation",
  data() {
    return {
      showUser: false
    };
  },
  methods: {
    scrollWatcher() {
      const scrollMonitor = require("scrollmonitor");
      const userElement = document.querySelector(".user-out");
      const userWatcher = scrollMonitor.create(userElement);
      userWatcher.enterViewport(() => {
        this.showUser = true;
      });
    }
  },
  mounted() {
    this.scrollWatcher();
  }
};
</script>

<style>
.user-out {
  margin-top: 100px;
}
.description {
  opacity: 0.6;
  font-family: "Black Han Sans", sans-serif;
}
.slide-user-enter-active {
  transition: all 2s ease;
}
.slide-user-enter {
  transform: translateY(150px);
  opacity: 0;
}
</style>
