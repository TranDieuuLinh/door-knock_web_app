DELETE FROM visits;
DELETE FROM tasks;
DELETE FROM household;
DELETE FROM users;

INSERT INTO users (user_id, name, email, address, territory, phone, password, campaign, user_status, created_at, role)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'John Smith',      'john.smith@doorknock.org',     '10 Capital St, Canberra ACT',   'ACT',             400000001, '$2a$10$mockpassword1', 'Federal Election 2026',    'ACTIVE',   '2026-06-01T09:00:00Z', 'ADMIN'),
    ('22222222-2222-2222-2222-222222222222', 'Sarah Johnson',   'sarah.johnson@doorknock.org',  '22 George St, Sydney NSW',      'Sydney West',     400000002, '$2a$10$mockpassword2', 'Federal Election 2026',    'ACTIVE',   '2026-06-01T09:01:00Z', 'COORDINATOR'),
    ('33333333-3333-3333-3333-333333333333', 'Michael Brown',   'michael.brown@doorknock.org',  '5 King St, Parramatta NSW',     'Sydney West',     400000003, '$2a$10$mockpassword3', 'Sydney West Campaign',     'ACTIVE',   '2026-06-02T10:00:00Z', 'DOORKNOCKER'),
    ('44444444-4444-4444-4444-444444444444', 'Emma Wilson',     'emma.wilson@doorknock.org',    '18 Collins St, Melbourne VIC',  'Melbourne North', 400000004, '$2a$10$mockpassword4', 'Melbourne North Campaign', 'ACTIVE',   '2026-06-02T10:01:00Z', 'DOORKNOCKER'),
    ('55555555-5555-5555-5555-555555555555', 'David Lee',       'david.lee@doorknock.org',      '3 Lonsdale St, Melbourne VIC',  'Melbourne North', 400000005, '$2a$10$mockpassword5', 'Melbourne North Campaign', 'ACTIVE',   '2026-06-03T11:00:00Z', 'COORDINATOR'),
    ('66666666-6666-6666-6666-666666666666', 'Jessica Taylor',  'jessica.taylor@doorknock.org', '9 Queen St, Brisbane QLD',       'Brisbane South',  400000006, '$2a$10$mockpassword6', 'Brisbane South Campaign',  'ACTIVE',   '2026-06-03T11:01:00Z', 'DOORKNOCKER'),
    ('77777777-7777-7777-7777-777777777777', 'Andrew White',    'andrew.white@doorknock.org',   '14 Adelaide St, Brisbane QLD',  'Brisbane South',  400000007, '$2a$10$mockpassword7', 'Brisbane South Campaign',  'ACTIVE',   '2026-06-04T12:00:00Z', 'DOORKNOCKER'),
    ('88888888-8888-8888-8888-888888888888', 'Olivia Green',    'olivia.green@doorknock.org',   '2 Murray St, Perth WA',         'Perth Metro',     400000008, '$2a$10$mockpassword8', 'Perth Metro Campaign',     'ON_LEAVE', '2026-06-04T12:01:00Z', 'DOORKNOCKER'),
    ('99999999-9999-9999-9999-999999999999', 'Liam Carter',     'liam.carter@doorknock.org',    '7 Hay St, Perth WA',            'Perth Metro',     400000009, '$2a$10$mockpassword9', 'Perth Metro Campaign',     'ACTIVE',   '2026-06-05T08:00:00Z', 'DOORKNOCKER'),
    -- Test user for GET /api/tasks/by-user?userEmail=abc@gmail.com (3 tasks below)
    ('7f3a9d2e-4b6c-4f8a-9d12-83e5b71c9a40', 'Anthony',         'abc@gmail.com',                '12 Campbell St, Toowoomba QLD', 'Toowoomba North', 400000010, '$2a$10$mockpassword10', 'Campaign A',               'ACTIVE',   '2026-06-05T08:30:00Z', 'ADMIN');

INSERT INTO household (household_id, address, suburb, note, postcode, phone, best_time, family_name)
VALUES
    ('a0000001-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '5 King St',       'Parramatta',  'Ring side door',           '2150', 412300001, '17:00:00', 'Brown'),
    ('a0000002-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '18 Collins St',   'Melbourne',   'Dog in yard',              '3000', 412300002, '18:30:00', 'Wilson'),
    ('a0000003-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '3 Lonsdale St',   'Melbourne',   'Prefer evenings',          '3000', 412300003, NULL,       'Lee'),
    ('a0000004-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '9 Queen St',      'Brisbane',    'Apartment unit 4',         '4000', 412300004, '16:00:00', 'Taylor'),
    ('a0000005-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11 Queen St',     'Brisbane',    'Next door to Taylor',      '4000', 412300005, '16:30:00', 'Nguyen'),
    ('a0000006-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '7 Hay St',        'Perth',       'New residents',            '6000', 412300006, '19:00:00', 'Carter'),
    ('a0000007-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '12 Campbell St',  'Toowoomba',   'Supports campaign',        '4350', 412300007, '17:30:00', 'Nguyen'),
    ('a0000008-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '14 Campbell St',  'Toowoomba',   'Busy mornings',            '4350', 412300008, '18:00:00', 'Chen'),
    ('a0000009-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '16 Campbell St',  'Toowoomba',   'Follow-up needed',         '4350', 412300009, NULL,       'Patel');

INSERT INTO tasks (task_id, assigned_date, updated_at, task_status, user_id, household_id)
VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2026-06-05T08:00:00Z', NULL,                    'PENDING',   '33333333-3333-3333-3333-333333333333', 'a0000001-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2026-06-05T09:00:00Z', NULL,                    'PENDING',   '44444444-4444-4444-4444-444444444444', 'a0000002-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', '2026-06-06T08:30:00Z', NULL,                    'PENDING',   '55555555-5555-5555-5555-555555555555', 'a0000003-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('dddddddd-dddd-dddd-dddd-dddddddddd01', '2026-06-06T09:00:00Z', NULL,                    'PENDING',   '66666666-6666-6666-6666-666666666666', 'a0000004-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('dddddddd-dddd-dddd-dddd-dddddddddd02', '2026-06-06T10:00:00Z', NULL,                    'PENDING',   '66666666-6666-6666-6666-666666666666', 'a0000005-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeee01', '2026-06-07T08:00:00Z', NULL,                    'PENDING',   '99999999-9999-9999-9999-999999999999', 'a0000006-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('f1f1f1f1-f1f1-f1f1-f1f1-f1f1f1f1f101', '2026-06-08T08:00:00Z', NULL,                    'PENDING',   '7f3a9d2e-4b6c-4f8a-9d12-83e5b71c9a40', 'a0000007-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('f1f1f1f1-f1f1-f1f1-f1f1-f1f1f1f1f102', '2026-06-09T09:00:00Z', '2026-06-10T14:00:00Z', 'COMPLETED', '7f3a9d2e-4b6c-4f8a-9d12-83e5b71c9a40', 'a0000008-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('f1f1f1f1-f1f1-f1f1-f1f1-f1f1f1f1f103', '2026-06-10T10:00:00Z', NULL,                    'PENDING',   '7f3a9d2e-4b6c-4f8a-9d12-83e5b71c9a40', 'a0000009-aaaa-aaaa-aaaa-aaaaaaaaaaaa');

INSERT INTO visits (visit_id, outcome, note, visited_at, visit_status, user_id, task_id)
VALUES
    ('11111111-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Interested',        'Requested policy flyer',           '2026-06-07T10:00:00Z', 'VISITED',   '33333333-3333-3333-3333-333333333333', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('11111111-aaaa-aaaa-aaaa-aaaaaaaaaaab', 'Not home',          'Left information card',            '2026-06-08T14:30:00Z', 'UNVISITED', '33333333-3333-3333-3333-333333333333', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('11111111-aaaa-aaaa-aaaa-aaaaaaaaaaac', 'Strong supporter',  'Signed up for volunteer shift',    '2026-06-10T16:45:00Z', 'VISITED',   '33333333-3333-3333-3333-333333333333', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),

    ('22222222-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Not home',          'Left information card',            '2026-06-07T11:00:00Z', 'UNVISITED', '44444444-4444-4444-4444-444444444444', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
    ('22222222-aaaa-aaaa-aaaa-aaaaaaaaaaab', 'Interested',        'Asked for follow-up call',         '2026-06-09T09:15:00Z', 'VISITED',   '44444444-4444-4444-4444-444444444444', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),

    ('33333333-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Interested',        'Wants yard sign',                  '2026-06-07T09:00:00Z', 'VISITED',   '66666666-6666-6666-6666-666666666666', 'dddddddd-dddd-dddd-dddd-dddddddddd01'),
    ('33333333-aaaa-aaaa-aaaa-aaaaaaaaaaab', 'Declined',          'Not interested in discussion',     '2026-06-08T11:30:00Z', 'VISITED',   '66666666-6666-6666-6666-666666666666', 'dddddddd-dddd-dddd-dddd-dddddddddd01'),
    ('33333333-aaaa-aaaa-aaaa-aaaaaaaaaaac', 'Not home',          'Will retry on weekend',            '2026-06-09T15:00:00Z', 'UNVISITED', '66666666-6666-6666-6666-666666666666', 'dddddddd-dddd-dddd-dddd-dddddddddd01'),
    ('33333333-aaaa-aaaa-aaaa-aaaaaaaaaaad', 'Interested',        'Registered for town hall',         '2026-06-10T10:20:00Z', 'VISITED',   '66666666-6666-6666-6666-666666666666', 'dddddddd-dddd-dddd-dddd-dddddddddd02'),
    ('33333333-aaaa-aaaa-aaaa-aaaaaaaaaaae', 'Strong supporter',  'Offered to host street meeting',   '2026-06-11T17:00:00Z', 'VISITED',   '66666666-6666-6666-6666-666666666666', 'dddddddd-dddd-dddd-dddd-dddddddddd02'),

    ('44444444-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Interested',        'New resident, open to chat',       '2026-06-08T13:00:00Z', 'VISITED',   '99999999-9999-9999-9999-999999999999', 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeee01'),

    ('55555555-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Interested',        'Wants more information',           '2026-06-08T09:00:00Z', 'VISITED',   '7f3a9d2e-4b6c-4f8a-9d12-83e5b71c9a40', 'f1f1f1f1-f1f1-f1f1-f1f1-f1f1f1f1f101'),
    ('55555555-aaaa-aaaa-aaaa-aaaaaaaaaaab', 'Not home',          'Will return tomorrow',             '2026-06-09T11:00:00Z', 'UNVISITED', '7f3a9d2e-4b6c-4f8a-9d12-83e5b71c9a40', 'f1f1f1f1-f1f1-f1f1-f1f1-f1f1f1f1f101'),
    ('55555555-aaaa-aaaa-aaaa-aaaaaaaaaaac', 'Strong supporter',  'Signed petition',                  '2026-06-10T15:30:00Z', 'VISITED',   '7f3a9d2e-4b6c-4f8a-9d12-83e5b71c9a40', 'f1f1f1f1-f1f1-f1f1-f1f1-f1f1f1f1f102');
