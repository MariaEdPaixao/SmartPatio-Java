-- Índices opcionais para melhorar performance futura

CREATE INDEX idx_moto_modelo ON moto (id_modelo_moto);
CREATE INDEX idx_usuario_filial ON usuario (id_filial);
CREATE INDEX idx_historico_moto ON historico_moto_filial (id_moto);
CREATE INDEX idx_historico_filial ON historico_moto_filial (id_filial);
