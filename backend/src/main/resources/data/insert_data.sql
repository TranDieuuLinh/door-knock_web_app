DELETE FROM visits;
DELETE FROM tasks;
DELETE FROM users;

INSERT INTO users (user_id, name, email, address, territory, phone, password, campaign, created_at, role)
VALUES
    ('11111111-1111-1111-1111-111111111111', 'John Smith',      'john.smith@doorknock.org',     '10 Capital St, Canberra ACT',   'ACT',             400000001, '$2a$10$mockpassword1', 'Federal Election 2026',    '2026-06-01T09:00:00Z', 'ADMIN'),
    ('22222222-2222-2222-2222-222222222222', 'Sarah Johnson',   'sarah.johnson@doorknock.org',  '22 George St, Sydney NSW',      'Sydney West',     400000002, '$2a$10$mockpassword2', 'Federal Election 2026',    '2026-06-01T09:01:00Z', 'COORDINATOR'),
    ('33333333-3333-3333-3333-333333333333', 'Michael Brown',   'michael.brown@doorknock.org',  '5 King St, Parramatta NSW',     'Sydney West',     400000003, '$2a$10$mockpassword3', 'Sydney West Campaign',     '2026-06-02T10:00:00Z', 'DOORKNOCKER'),
    ('44444444-4444-4444-4444-444444444444', 'Emma Wilson',     'emma.wilson@doorknock.org',    '18 Collins St, Melbourne VIC',  'Melbourne North', 400000004, '$2a$10$mockpassword4', 'Sydney West Campaign',     '2026-06-02T10:01:00Z', 'DOORKNOCKER'),
    ('55555555-5555-5555-5555-555555555555', 'David Lee',       'david.lee@doorknock.org',      '3 Lonsdale St, Melbourne VIC',  'Melbourne North', 400000005, '$2a$10$mockpassword5', 'Melbourne North Campaign', '2026-06-03T11:00:00Z', 'COORDINATOR'),
    ('66666666-6666-6666-6666-666666666666', 'Jessica Taylor',  'jessica.taylor@doorknock.org', '9 Queen St, Brisbane QLD',       'Brisbane South',  400000006, '$2a$10$mockpassword6', 'Melbourne North Campaign', '2026-06-03T11:01:00Z', 'DOORKNOCKER'),
    ('77777777-7777-7777-7777-777777777777', 'Andrew White',    'andrew.white@doorknock.org',   '14 Adelaide St, Brisbane QLD',  'Brisbane South',  400000007, '$2a$10$mockpassword7', 'Brisbane South Campaign',  '2026-06-04T12:00:00Z', 'DOORKNOCKER'),
    ('88888888-8888-8888-8888-888888888888', 'Olivia Green',    'olivia.green@doorknock.org',   '2 Murray St, Perth WA',         'Perth Metro',     400000008, '$2a$10$mockpassword8', 'Brisbane South Campaign',  '2026-06-04T12:01:00Z', 'COORDINATOR');

INSERT INTO tasks (task_id, content, assigned_date, updated_at, task_status, user_id)
VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Knock doors on Capital St',       '2026-06-05T08:00:00Z', NULL, 'PENDING', '33333333-3333-3333-3333-333333333333'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Follow up George St households',  '2026-06-05T09:00:00Z', NULL, 'PENDING', '44444444-4444-4444-4444-444444444444'),
    ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Coordinate Melbourne North zone', '2026-06-06T08:30:00Z', NULL, 'PENDING', '55555555-5555-5555-5555-555555555555');

INSERT INTO visits (visit_id, outcome, note, visited_at, visit_status, user_id, task_id)
VALUES
    ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Interested', 'Requested policy flyer', '2026-06-07T10:00:00Z', 'VISITED',   '33333333-3333-3333-3333-333333333333', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
    ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Not home',   'Left information card',  '2026-06-07T11:00:00Z', 'UNVISITED', '44444444-4444-4444-4444-444444444444', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb');
