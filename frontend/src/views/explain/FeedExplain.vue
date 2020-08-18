<template>
  <div class="mypage-explain">
    <div>여기는 피드설명</div>
    <div class="container-mypage">
      <div class="one"></div>
      <div class="two"></div>
      <div class="thr"></div>
      <div class="fou"></div>
      <div class="fiv"></div>
    </div>
    <button class="prevbtn" @click="ne">
      <i class="mdi-arrow mdi mdi-arrow-left-bold-circle-outline"></i>
    </button>
    <button class="nextbtn" @click="pl">
      <i class="mdi-arrow mdi mdi-arrow-right-bold-circle-outline"></i>
    </button>
  </div>
</template>

<script>
export default {
  name: "FeedExplain",
  data() {
    return {
      imgList: ["black", "red", "yellow", "blue", "green"],
      a: 0,
      b: 1,
      c: 2,
      d: 3,
      e: 4
    };
  },
  methods: {
    change() {
      if (this.a < 4) {
        this.a++;
      } else {
        this.a = 0;
      }
      if (this.b < 4) {
        this.b++;
      } else {
        this.b = 0;
      }
      if (this.c < 4) {
        this.c++;
      } else {
        this.c = 0;
      }
      if (this.d < 4) {
        this.d++;
      } else {
        this.d = 0;
      }
      if (this.e < 4) {
        this.e++;
      } else {
        this.e = 0;
      }
    },
    changepr() {
      if (this.a > 0) {
        this.a--;
      } else {
        this.a = 4;
      }
      if (this.b > 0) {
        this.b--;
      } else {
        this.b = 4;
      }
      if (this.c > 0) {
        this.c--;
      } else {
        this.c = 4;
      }
      if (this.d > 0) {
        this.d--;
      } else {
        this.d = 4;
      }
      if (this.e > 0) {
        this.e--;
      } else {
        this.e = 4;
      }
    },
    move() {
      const boxone = document.querySelector(".one");
      const boxtwo = document.querySelector(".two");
      const boxthr = document.querySelector(".thr");
      const boxfou = document.querySelector(".fou");
      const boxfiv = document.querySelector(".fiv");
      const nextbtn = document.querySelector(".nextbtn");
      const prevbtn = document.querySelector(".prevbtn");
      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      boxone.style.animation = "one 1s forwards";
      boxtwo.style.animation = "two 1s forwards";
      boxthr.style.animation = "thr 1s forwards";
      boxfou.style.animation = "fou 1s forwards";
      boxfiv.style.animation = "fiv 1s forwards";
    },
    movepr() {
      const boxone = document.querySelector(".one");
      const boxtwo = document.querySelector(".two");
      const boxthr = document.querySelector(".thr");
      const boxfou = document.querySelector(".fou");
      const boxfiv = document.querySelector(".fiv");
      const nextbtn = document.querySelector(".nextbtn");
      const prevbtn = document.querySelector(".prevbtn");

      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      boxone.style.animation = "backone 1s forwards";
      boxtwo.style.animation = "backtwo 1s forwards";
      boxthr.style.animation = "backthr 1s forwards";
      boxfou.style.animation = "backfou 1s forwards";
      boxfiv.style.animation = "backfiv 1s forwards";
    },
    stop() {
      const boxone = document.querySelector(".one");
      const boxtwo = document.querySelector(".two");
      const boxthr = document.querySelector(".thr");
      const boxfou = document.querySelector(".fou");
      const boxfiv = document.querySelector(".fiv");
      const nextbtn = document.querySelector(".nextbtn");
      const prevbtn = document.querySelector(".prevbtn");

      boxone.style.animation = "";
      boxtwo.style.animation = "";
      boxthr.style.animation = "";
      boxfou.style.animation = "";
      boxfiv.style.animation = "";

      boxone.style.left = -55 + "%";
      boxone.style.zIndex = 1;
      boxone.style.transform = "scale(0.5)";
      boxone.style.backgroundColor = `${this.imgList[this.a]}`;

      boxtwo.style.left = 15 + "%";
      boxtwo.style.zIndex = 2;
      boxtwo.style.transform = "scale(0.8)";
      boxtwo.style.backgroundColor = `${this.imgList[this.b]}`;
      // boxtwo.style.background = `url('${imgList[b]}')`

      boxthr.style.left = 85 + "%";
      boxthr.style.zIndex = 3;
      boxthr.style.transform = "scale(1)";
      boxthr.style.backgroundColor = `${this.imgList[this.c]}`;

      boxfou.style.left = 155 + "%";
      boxfou.style.zIndex = 2;
      boxfou.style.transform = "scale(0.8)";
      boxfou.style.backgroundColor = `${this.imgList[this.d]}`;

      boxfiv.style.left = 225 + "%";
      boxfiv.style.zIndex = 1;
      boxfiv.style.transform = "scale(0.5)";
      boxfiv.style.backgroundColor = `${this.imgList[this.e]}`;

      nextbtn.removeAttribute("disabled");
      prevbtn.removeAttribute("disabled");
    },
    pl() {
      this.move();
      setTimeout(this.change, 1001);
      setTimeout(this.stop, 1002);
    },
    ne() {
      this.movepr();
      setTimeout(this.changepr, 1001);
      setTimeout(this.stop, 1002);
    }
  }
};
</script>

<style>
.mdi-arrow {
  font-size: 41px;
}

.mypage-explain {
  margin-top: 70px;
}
.container-mypage {
  position: relative;
  width: 300px;
  height: 450px;
}
.container-mypage div {
  width: 550px;
  height: 450px;
  position: absolute;
}
.one {
  background-color: black;
  transform: scale(0.5);
  left: -55%;
  z-index: 1;
}
.two {
  background-color: red;
  transform: scale(0.8);
  left: 15%;
  z-index: 2;
}
.thr {
  background-color: yellow;
  transform: scale(1);
  left: 85%;
  z-index: 3;
}
.fou {
  background-color: blue;
  transform: scale(0.8);
  left: 155%;
  z-index: 2;
}
.fiv {
  background-color: green;
  transform: scale(0.5);
  left: 225%;
  z-index: 1;
}
@keyframes one {
  100% {
    transform: scale(0.5);
    left: 225%;
    z-index: 1;
  }
}
@keyframes two {
  100% {
    transform: scale(0.5);
    left: -55%;
    z-index: 1;
  }
}
@keyframes thr {
  100% {
    transform: scale(0.8);
    left: 15%;
    z-index: 2;
  }
}
@keyframes fou {
  100% {
    transform: scale(1);
    left: 85%;
    z-index: 3;
  }
}
@keyframes fiv {
  100% {
    transform: scale(0.8);
    left: 155%;
    z-index: 2;
  }
}
@keyframes backone {
  100% {
    transform: scale(0.8);
    left: 15%;
    z-index: 2;
  }
}
@keyframes backtwo {
  100% {
    transform: scale(1);
    left: 85%;
    z-index: 3;
  }
}
@keyframes backthr {
  100% {
    transform: scale(0.8);
    left: 155%;
    z-index: 2;
  }
}
@keyframes backfou {
  100% {
    transform: scale(0.5);
    left: 225%;
    z-index: 1;
  }
}
@keyframes backfiv {
  100% {
    transform: scale(0.5);
    left: -55%;
    z-index: 1;
  }
}
</style>
