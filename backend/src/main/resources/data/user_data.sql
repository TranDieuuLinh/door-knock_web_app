-- Populate mock users for testing
DELETE FROM users;

INSERT INTO users (id, name, email, phone, password, campaign, created_at, role)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'John Smith',      'john.smith@doorknock.org',    400000001, '$2a$10$mockpassword1', 'Federal Election 2026',      TIMESTAMP '2026-06-01 09:00:00', 'ADMIN'),
    ('22222222-2222-2222-2222-222222222222', 'Sarah Johnson',   'sarah.johnson@doorknock.org',  400000002, '$2a$10$mockpassword2', 'Federal Election 2026',      TIMESTAMP '2026-06-01 09:01:00', 'COORDINATOR'),
    ('33333333-3333-3333-3333-333333333333', 'Michael Brown',   'michael.brown@doorknock.org',  400000003, '$2a$10$mockpassword3', 'Sydney West Campaign',       TIMESTAMP '2026-06-02 10:00:00', 'DOORKNOCKER'),
    ('44444444-4444-4444-4444-444444444444', 'Emma Wilson',     'emma.wilson@doorknock.org',    400000004, '$2a$10$mockpassword4', 'Sydney West Campaign',       TIMESTAMP '2026-06-02 10:01:00', 'DOORKNOCKER'),
    ('55555555-5555-5555-5555-555555555555', 'David Lee',       'david.lee@doorknock.org',      400000005, '$2a$10$mockpassword5', 'Melbourne North Campaign',   TIMESTAMP '2026-06-03 11:00:00', 'COORDINATOR'),
    ('66666666-6666-6666-6666-666666666666', 'Jessica Taylor',  'jessica.taylor@doorknock.org', 400000006, '$2a$10$mockpassword6', 'Melbourne North Campaign',   TIMESTAMP '2026-06-03 11:01:00', 'DOORKNOCKER'),
    ('77777777-7777-7777-7777-777777777777', 'Andrew White',    'andrew.white@doorknock.org',   400000007, '$2a$10$mockpassword7', 'Brisbane South Campaign',    TIMESTAMP '2026-06-04 12:00:00', 'DOORKNOCKER'),
    ('88888888-8888-8888-8888-888888888888', 'Olivia Green',    'olivia.green@doorknock.org',   400000008, '$2a$10$mockpassword8', 'Brisbane South Campaign',    TIMESTAMP '2026-06-04 12:01:00', 'COORDINATOR');
