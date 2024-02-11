-- User table seeding
INSERT INTO "user" (Name, Email, Password, PhoneNumber, IsActive, LastLogin)
VALUES
    ('John Doe', 'john@example.com', 'password123', '1234567890', 1, CURRENT_TIMESTAMP),
    ('Jane Smith', 'jane@example.com', 'password456', '0987654321', 1, CURRENT_TIMESTAMP);

-- ResetPassword table seeding
INSERT INTO "reset_password" (UserId, OTP, ExpiredDate)
VALUES
    (1, 123456, CURRENT_TIMESTAMP + INTERVAL '1' DAY),
    (2, 987654, CURRENT_TIMESTAMP + INTERVAL '1' DAY);

-- Authentication table seeding
INSERT INTO "authentication" (UserId, Token, ExpiredDate)
VALUES
    (1, 'token123', CURRENT_TIMESTAMP + INTERVAL '7' DAY),
    (2, 'token456', CURRENT_TIMESTAMP + INTERVAL '7' DAY);

-- CreditCard table seeding
INSERT INTO "credit_card" (CardNumber, UserId, CVV, ExpiredDate, CardHolder)
VALUES
    ('1234567890123456', 1, '123', CURRENT_TIMESTAMP + INTERVAL '1' YEAR, 'John Doe'),
    ('9876543210987654', 2, '456', CURRENT_TIMESTAMP + INTERVAL '1' YEAR, 'Jane Smith');

-- Subscription table seeding
INSERT INTO "subscription" (UserId, ServiceId, StartDate, EndDate, TotalMeeting, Status)
VALUES
    (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1' MONTH, 10, 1),
    (2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1' MONTH, 15, 1);

-- Service table seeding
INSERT INTO "service" (Name, MeetingPrice, TotalMeeting, Duration)
VALUES
    ('Gym Membership', 50.00, 20, 60.0),
    ('Yoga Classes', 30.00, 20, 60.0);

-- WorkOutLog table seeding
INSERT INTO "workout_log" (SubcriptionId, DayOfWeek, Duration, StartTime)
VALUES
    (1, 1, 60.0, CURRENT_TIMESTAMP),
    (2, 2, 60.0, CURRENT_TIMESTAMP);

-- Payment table seeding
INSERT INTO "payment" (UserId, ServiceId, Amount, OTP, PaymentStatus, ExpiredDate, PaymentDate)
VALUES
    (1, 1, 50.00, 123456, 1, CURRENT_TIMESTAMP + INTERVAL '1' DAY, CURRENT_TIMESTAMP),
    (2, 2, 30.00, 987654, 1, CURRENT_TIMESTAMP + INTERVAL '1' DAY, CURRENT_TIMESTAMP);
