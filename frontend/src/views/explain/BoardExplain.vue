<template>
  <div class="explain-board">
    <div class="helper-text">보드<i class="mdi mdi-book-open"></i></div>
    <div class="container-board">
      <div class="two-board"></div>
      <div class="thr-board"></div>
      <div class="fou-board"></div>
    </div>
    <button class="prevbtn-board" @click="ne">
      <i class="mdi-arrow-board mdi mdi-arrow-left-bold-circle-outline"></i>
    </button>
    <button class="nextbtn-board" @click="pl">
      <i class="mdi-arrow mdi mdi-arrow-right-bold-circle-outline"></i>
    </button>
  </div>
</template>

<script>
export default {
  name: "BoardExplain",
  data() {
    return {
      imgList: [
        require("@/assets/board3.png"),
        require("@/assets/board1.png"),
        require("@/assets/board2.png")
      ],
      a: 0,
      b: 1,
      c: 2
    };
  },
  methods: {
    change() {
      if (this.a < 2) {
        this.a++;
      } else {
        this.a = 0;
      }
      if (this.b < 2) {
        this.b++;
      } else {
        this.b = 0;
      }
      if (this.c < 2) {
        this.c++;
      } else {
        this.c = 0;
      }
    },
    changepr() {
      if (this.a > 0) {
        this.a--;
      } else {
        this.a = 2;
      }
      if (this.b > 0) {
        this.b--;
      } else {
        this.b = 2;
      }
      if (this.c > 0) {
        this.c--;
      } else {
        this.c = 2;
      }
    },
    move() {
      const boxtwo = document.querySelector(".two-board");
      const boxthr = document.querySelector(".thr-board");
      const boxfou = document.querySelector(".fou-board");
      const nextbtn = document.querySelector(".nextbtn-board");
      const prevbtn = document.querySelector(".prevbtn-board");
      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      boxtwo.style.animation = "two-board 1s forwards";
      boxthr.style.animation = "thr-board 1s forwards";
      boxfou.style.animation = "fou-board 1s forwards";
    },
    movepr() {
      const boxtwo = document.querySelector(".two-board");
      const boxthr = document.querySelector(".thr-board");
      const boxfou = document.querySelector(".fou-board");
      const nextbtn = document.querySelector(".nextbtn-board");
      const prevbtn = document.querySelector(".prevbtn-board");

      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      boxtwo.style.animation = "backtwo-board 1s forwards";
      boxthr.style.animation = "backthr-board 1s forwards";
      boxfou.style.animation = "backfou-board 1s forwards";
    },
    stop() {
      const boxtwo = document.querySelector(".two-board");
      const boxthr = document.querySelector(".thr-board");
      const boxfou = document.querySelector(".fou-board");
      const nextbtn = document.querySelector(".nextbtn-board");
      const prevbtn = document.querySelector(".prevbtn-board");

      boxtwo.style.animation = "";
      boxthr.style.animation = "";
      boxfou.style.animation = "";

      boxtwo.style.left = 15 + "%";
      boxtwo.style.zIndex = 2;
      boxtwo.style.transform = "scale(0.7)";
      boxtwo.style.backgroundImage = `url('${this.imgList[this.a]}')`;

      boxthr.style.left = 85 + "%";
      boxthr.style.zIndex = 3;
      boxthr.style.transform = "scale(1.15)";
      boxthr.style.backgroundImage = `url('${this.imgList[this.b]}')`;

      boxfou.style.left = 155 + "%";
      boxfou.style.zIndex = 2;
      boxfou.style.transform = "scale(0.7)";
      boxfou.style.backgroundImage = `url('${this.imgList[this.c]}')`;

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
.mdi-arrow-board {
  font-size: 41px;
}

.explain-board {
  margin-top: 40px;
}
.container-board {
  position: relative;
  width: 300px;
  height: 450px;
}
.container-board div {
  width: 550px;
  height: 450px;
  position: absolute;
}
.two-board {
  background-color: black;
  background-image: url("../../assets/board3.png");
  background-size: cover;
  transform: scale(0.7);
  left: 15%;
  z-index: 2;
}
.thr-board {
  background-color: black;
  background-image: url("../../assets/board1.png");
  background-size: cover;
  transform: scale(1.15);
  left: 85%;
  z-index: 3;
}
.fou-board {
  background-color: black;
  background-image: url("../../assets/board2.png");
  background-size: cover;
  transform: scale(0.7);
  left: 155%;
  z-index: 2;
}
@keyframes two-board {
  100% {
    transform: scale(0.7);
    left: 155%;
    z-index: 2;
  }
}
@keyframes thr-board {
  100% {
    transform: scale(0.7);
    left: 15%;
    z-index: 2;
  }
}
@keyframes fou-board {
  100% {
    transform: scale(1.15);
    left: 85%;
    z-index: 3;
  }
}
@keyframes backtwo-board {
  100% {
    transform: scale(1.15);
    left: 85%;
    z-index: 3;
  }
}
@keyframes backthr-board {
  100% {
    transform: scale(0.7);
    left: 155%;
    z-index: 2;
  }
}
@keyframes backfou-board {
  100% {
    transform: scale(0.7);
    left: 15%;
    z-index: 2;
  }
}
</style>
