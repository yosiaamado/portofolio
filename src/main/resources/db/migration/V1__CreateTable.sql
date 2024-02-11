
CREATE TABLE IF NOT EXISTS "user" (
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(255),
    Email VARCHAR(128),
    Password VARCHAR(255),
    PhoneNumber VARCHAR(16),
    IsActive INT,
    LastLogin TIMESTAMP,
    IsLogged INT
);

CREATE TABLE IF NOT EXISTS "reset_password" (
    Id SERIAL PRIMARY KEY,
    UserId BIGINT NOT NULL,
    OTP INT NOT NULL,
    ExpiredDate TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS "authentication" (
    Id SERIAL PRIMARY KEY,
    UserId BIGINT NOT NULL,
    Token VARCHAR(255) NOT NULL,
    ExpiredDate TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS "credit_card" (
    CardNumber VARCHAR(32) PRIMARY KEY,
    UserId BIGINT NOT NULL,
    CVV VARCHAR(3) NOT NULL,
    ExpiredDate TIMESTAMP NOT NULL,
    CardHolder VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS "subscription" (
    Id SERIAL PRIMARY KEY,
    UserId BIGINT NOT NULL,
    ServiceId BIGINT NOT NULL,
    StartDate TIMESTAMP NOT NULL,
    EndDate TIMESTAMP,
    TotalMeeting INT NOT NULL,
    Status INT NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS "service" (
    Id SERIAL PRIMARY KEY,
    Name VARCHAR(128) NOT NULL,
    MeetingPrice FLOAT NOT NULL,
    TotalMeeting INT NOT NULL,
    Duration FLOAT
);

CREATE TABLE IF NOT EXISTS "workout_log" (
    Id SERIAL PRIMARY KEY,
    SubcriptionId BIGINT NOT NULL,
    DayOfWeek INT NOT NULL,
    Duration FLOAT,
    StartTime TIMESTAMP
);

CREATE TABLE IF NOT EXISTS "payment" (
    Id SERIAL PRIMARY KEY,
    UserId BIGINT NOT NULL,
    ServiceId BIGINT NOT NULL,
    Amount FLOAT,
    OTP INT NOT NULL,
    PaymentStatus INT NOT NULL DEFAULT 0,
    ExpiredDate TIMESTAMP NOT NULL,
    PaymentDate TIMESTAMP
);