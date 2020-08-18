<template>
  <div class="user-info">
    <div class="show-userbox p-5">
      <transition name="slide-user">
        <h3 v-if="showUser" class="d-flex justify-content-center">
          <b>우리의 사용자들</b>
        </h3>
      </transition>
      <transition name="slide-user">
        <div v-if="showUser" class="container d-flex justify-content-around">
          <div
            align="center"
            class="user-box"
            style="border:15px solid #ad249f"
          >
            <div class="user-text">
              취준생
            </div>
            <i class="mdi-user mdi mdi-tie"></i>
          </div>

          <div
            align="center"
            class="user-box"
            style="border:15px solid #df3277"
          >
            <div class="user-text">개발자</div>
            <i class="mdi-user mdi mdi-laptop-chromebook"></i>
          </div>

          <div
            align="center"
            class="user-box"
            style="border:15px solid #00d59b;"
          >
            <div class="user-text">기타 등등</div>
            <i class="mdi-user mdi mdi-account-multiple-plus"></i>
          </div>
        </div>
      </transition>
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
      const userElement = document.querySelector(".show-userbox");
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
.user-info {
  /* background-color: #17212c; */
}
.user-box {
  width: 230px;
  height: 230px;
  background-color: #17212c;
  color: white;
  margin: auto;
}

.user-text {
  font-size: 25px;
  margin-top: 50px;
  margin-bottom: 45px;
  font-family: "Black Han Sans", sans-serif;
}

.slide-user-enter-active {
  transition: all 2s ease;
}
.slide-user-enter {
  transform: translateY(150px);
  opacity: 0;
}
.mdi-user {
  font-size: 40px;
}
</style>
