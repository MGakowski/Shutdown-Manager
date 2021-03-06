void setup() {
  size(350, 480);
}

float backC = 255;
int hourX;
int entPressed = 0;
int setHour = 1;
int setMin = 0;
int click = 0;
int activated = 0;
String program = "C:/shutdown.bat";

void draw() {
  clock();
  hourMin();
  active();
  clickHour();
  clickMin();
  printTimes();
  clickActivate();
  shutdown();
}

void cbc() {//background function
  while (backC > 20) {
    background(0, 0, backC);
    backC = backC - 1;
  }
}

void clock() {//Clock function
  textSize(15);
  fill(#A09AEA);

  if (hour() < 18 || hour() < 5) {
    backC = 99;
    background(0, 0, backC);
  } else {
    cbc();
    background(0, 0, backC);
  }

  if (hour() > 12) {
    hourX = hour() - 12;
  } else {
    hourX = hour();
  }

  text(hourX + " : " + minute() + " : " + second(), 10, 460);
  if (hour() > 12) {
    text("-pm", 100, 460);
  } else {
    text("-am", 100, 460);
  }
}

void hourMin() {
  textSize(32);
  text("Enter Hour: ", 10, 100);
  text("Enter Minute: ", 10, 200);
  noFill();
  stroke(#A09AEA);
  rect(190, 65, 40, 40);//Hour box
  rect(220, 165, 40, 40);//Min box
}

void active() {
  noFill();
  stroke(#A09AEA);
  rect(10, 270, 175, 45);
  textSize(25);
  text("Active", 15, 300);
  if (activated == 0) {
    noStroke();
    fill(0, 50, 0);
    ellipse(115, 293, 22, 22);
    fill(255, 0, 0);//Red Circle
    ellipse(155, 293, 22, 22);
  } else {
    noStroke();
    fill(0, 255, 0);//Green Circle
    ellipse(115, 293, 22, 22);
    fill(50, 0, 0);
    ellipse(155, 293, 22, 22);
  }
}

void clickActivate() {//Click Activate
  if (click == 1 && mouseX > 10 && mouseX < 185 && mouseY > 270 && mouseY < 320 && activated == 0) {
    activated=1;
    click=0;
  } 
  if (click == 1 && mouseX > 10 && mouseX < 185 && mouseY > 270 && mouseY < 320 && activated == 1) {
    activated=0;
    click=0;
  }
}

void clickHour() {//Click Hour
  if (click == 1 && mouseX > 190 && mouseX < 230 && mouseY > 65 && mouseY < 105 && setHour < 24 ) {
    setHour=setHour+1;
    click=0;
  }
  if ((click == 1 && mouseX > 190 && mouseX < 230 && mouseY > 65 && mouseY < 105 && setHour == 24 )) {
    setHour=1;
    click=0;
  }
}

void clickMin() {//Click Min
  if (click == 1 && mouseX > 220 && mouseX < 260 && mouseY > 165 && mouseY < 205 && setMin < 59) {
    setMin=setMin+1;
    click=0;
  } 
  if (click == 1 && mouseX > 220 && mouseX < 260 && mouseY > 165 && mouseY < 205 && setMin == 59) {
    setMin=0;
    click=0;
  }
}

void printTimes() {
  textSize(30);
  fill(#A09AEA);
  text(setHour, 192, 98);//Hour print
  text(setMin, 222, 198);//Min print
}

void mouseClicked() {
  if (click == 0) {
    click = 1;
  } else {
    click = 0;
  }
}

void shutdown() {
  if (minute() == setMin && hour() == setHour && activated == 1) {
    println("PC Shutdown!");
    open(program);
    activated = 0;
    System.exit(0);
  }
}
