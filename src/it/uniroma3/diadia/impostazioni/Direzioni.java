package it.uniroma3.diadia.impostazioni;

public enum Direzioni {
	
	NORD() {
		@Override public Direzioni opposta() {
			return SUD;
		}
	},
	
	SUD {
		@Override
		public Direzioni opposta() {
			return NORD;
		}
	},
	
	OVEST() {
		@Override public Direzioni opposta() {
			return EST;
		}
	}, 
	
	EST {
		@Override
		public Direzioni opposta() {
			return OVEST;
		}
	};
	
	public abstract Direzioni opposta();
}
