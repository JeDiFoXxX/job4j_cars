CREATE TABLE IF NOT EXISTS history (
    id BIGSERIAL PRIMARY KEY,
    start_at DATE NOT NULL,
    end_at DATE,
    CONSTRAINT chk_dates_order CHECK (end_at IS NULL OR end_at >= start_at),
    CONSTRAINT chk_dates_not_future CHECK (start_at <= CURRENT_DATE AND (end_at IS NULL OR end_at <= CURRENT_DATE))
);