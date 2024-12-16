CREATE TABLE IF NOT EXISTS public.conta (
    numero VARCHAR(20) NOT NULL,
    saldo DOUBLE PRECISION,
    PRIMARY KEY (numero)
);

CREATE TABLE IF NOT EXISTS public.conta_especial (
    numero VARCHAR(20) NOT NULL,
    limite DOUBLE PRECISION,
    PRIMARY KEY (numero),
    FOREIGN KEY (numero) REFERENCES public.conta (numero)
);

CREATE TABLE IF NOT EXISTS public.conta_deb_especial (
    numero VARCHAR(20) NOT NULL,
    PRIMARY KEY (numero),
    FOREIGN KEY (numero) REFERENCES public.conta_especial (numero)
);

CREATE TABLE IF NOT EXISTS public.conta_normal (
    numero VARCHAR(20) NOT NULL,
    PRIMARY KEY (numero)
);
