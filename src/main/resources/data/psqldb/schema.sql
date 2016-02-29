DROP TABLE IF EXISTS brand;


CREATE TABLE brand
(
  brand_id integer NOT NULL,
  brand_name character varying(255),
  brand_description character varying(5000),
  brand_namecn character varying(255),
  brand_descriptioncn character varying(5000),
  CONSTRAINT brand_pkey PRIMARY KEY (brand_id)
)
WITH (
  OIDS=FALSE
);

ALTER TABLE brand
  OWNER TO postgres;

DROP TABLE IF EXISTS product;

CREATE TABLE product
(
  bar_code character varying(255) NOT NULL,
  additives character varying(5000),
  allergy_info character varying(5000),
  analytical_consituents character varying(5000),
  brand character varying(255),
  company_address character varying(255),
  description character varying(5000),
  img character varying(255),
  img_alt character varying(255),
  ingredients_0 character varying(5000),
  ingredients_1 character varying(5000),
  ingredients_2 character varying(5000),
  link character varying(255),
  mandatory_info_0 character varying(5000),
  mandatory_info_1 character varying(5000),
  name character varying(255),
  notes_0 character varying(5000),
  notes_1 character varying(5000),
  notes_2 character varying(5000),
  nutritional_info character varying(4000),
  place_of_origin character varying(100),
  price real,
  registration_number character varying(100),
  size character varying(255),
  statement character varying(5000),
  warnings_0 character varying(5000),
  warnings_1 character varying(5000),
  warnings_2 character varying(5000),
  CONSTRAINT product_pkey PRIMARY KEY (bar_code)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE product
  OWNER TO postgres;
