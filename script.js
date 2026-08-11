let secretNumber = "";
let digits = 4;
let attempts = 0;
let hintsLeft = 2;
let hintsUsed = 0;
let revealedPositions = [];
function startGame(numberOfDigits) {
    revealedPositions = [];
    digits = numberOfDigits;
    attempts = 0;
    hintsUsed = 0;

    hintsLeft = Math.floor(digits / 2);

    secretNumber = generateNumber(digits);

    document.getElementById("digit-count").textContent = digits;
    document.getElementById("attempts").textContent = attempts;
    document.getElementById("hints").textContent = hintsLeft;

    document.getElementById("correct-digits").textContent = "0";
    document.getElementById("correct-positions").textContent = "0";
    document.getElementById("hint-message").textContent = "";

    document.getElementById("difficulty-screen").classList.add("hidden");
    document.getElementById("game-screen").classList.remove("hidden");
    document.getElementById("win-screen").classList.add("hidden");

    document.getElementById("guess-input").value = "";
    document.getElementById("guess-input").focus();

    console.log("Secret number:", secretNumber);
}


function generateNumber(length) {

    let number = "";

    for (let i = 0; i < length; i++) {

        let digit = Math.floor(Math.random() * 10);

        if (i === 0 && digit === 0) {
            digit = Math.floor(Math.random() * 9) + 1;
        }

        number += digit;
    }

    return number;
}


function checkGuess() {

    let input = document.getElementById("guess-input");
    let guess = input.value.trim();

    if (!/^\d+$/.test(guess)) {
        showMessage("Please enter digits only.");
        return;
    }

    if (guess.length !== digits) {
        showMessage("Please enter exactly " + digits + " digits.");
        return;
    }

    attempts++;

    document.getElementById("attempts").textContent = attempts;

    let correctPositions = 0;
    let correctDigits = 0;

    for (let i = 0; i < digits; i++) {

        if (secretNumber[i] === guess[i]) {
            correctPositions++;
        }
    }

    let used = new Array(digits).fill(false);

    for (let i = 0; i < digits; i++) {

        for (let j = 0; j < digits; j++) {

            if (!used[j] && secretNumber[j] === guess[i]) {

                correctDigits++;
                used[j] = true;
                break;
            }
        }
    }

    document.getElementById("correct-digits").textContent = correctDigits;
    document.getElementById("correct-positions").textContent = correctPositions;

    if (correctPositions === digits) {
        winGame();
        return;
    }

    showMessage("Keep trying!");
    input.value = "";
    input.focus();
}


function useHint() {

    if (hintsLeft === 0) {
        document.getElementById("hint-message").textContent =
            "No hints remaining!";
        return;
    }

    let hint;

    if (hintsUsed === 0) {

        let hasRepeated = hasRepeatedDigits();

        if (hasRepeated) {
            hint = "The number contains repeated digits.";
        } else {
            hint = "The number does not contain repeated digits.";
        }

    } else {

        let position = getRandomUnrevealedPosition();

        hint =
            "Position " +
            (position + 1) +
            " contains the digit " +
            secretNumber[position] +
            ".";
    }

    hintsUsed++;
    hintsLeft--;

    document.getElementById("hints").textContent = hintsLeft;
    document.getElementById("hint-message").textContent = hint;
}


function hasRepeatedDigits() {

    for (let i = 0; i < secretNumber.length; i++) {

        for (let j = i + 1; j < secretNumber.length; j++) {

            if (secretNumber[i] === secretNumber[j]) {
                return true;
            }
        }
    }

    return false;
}


function getRandomUnrevealedPosition() {

    let available = [];

    for (let i = 0; i < digits; i++) {

        if (!revealedPositions.includes(i)) {
            available.push(i);
        }
    }

    let position = available[Math.floor(Math.random() * available.length)];

    revealedPositions.push(position);

    return position;
}


function winGame() {

    document.getElementById("game-screen").classList.add("hidden");
    document.getElementById("win-screen").classList.remove("hidden");

    document.getElementById("secret-display").textContent = secretNumber;
    document.getElementById("final-attempts").textContent = attempts;
}


function restartGame() {

    document.getElementById("difficulty-screen").classList.remove("hidden");
    document.getElementById("game-screen").classList.add("hidden");
    document.getElementById("win-screen").classList.add("hidden");

    document.getElementById("guess-input").value = "";
}


function showMessage(message) {

    document.getElementById("message").textContent = message;
}